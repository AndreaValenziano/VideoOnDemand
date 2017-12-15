package com.videoondemand.control;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.dto.FilmDTO;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;
import com.videoondemand.utils.CustomTags;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
@WebServlet("/FilmController")
@MultipartConfig
public class FilmControllerServlet extends HttpServlet {
    private static final String YEAR_ERROR = "Invalid year";
    private static final String YEAR_RANGE_ERROR = "The year must be between 1920 and ";
    private static final String TITLE_ERROR = "Title length is shorter than 2 character";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        setPageValues(request);

        List<String> errors = new ArrayList<>();
        HashMap<String, String> defaultFilm = new HashMap<>();
        defaultFilm.put("title", "");
        defaultFilm.put("gender", "");
        defaultFilm.put("year", "");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        title = title != null ? title.trim() : "";

        int id = Integer.parseInt(request.getParameter("id"));

        String yearStr = request.getParameter("year");
        yearStr = yearStr != null ? yearStr.trim() : "";
        int year = -1;
        int reqGender;

        try {
            reqGender = Integer.parseInt(request.getParameter("genre"));
        } catch (Exception e) {
            e.printStackTrace();
            reqGender = 0;
        }


        try {
            year = Integer.parseInt(yearStr);
            if (year < 1920 || year > LocalDate.now().getYear()) {
                errors.add(YEAR_RANGE_ERROR + LocalDate.now().getYear());
            }
        } catch (NumberFormatException e) {
            errors.add(YEAR_ERROR);
        }

        if (title.length() < 2) {


            errors.add(TITLE_ERROR);
        }

        if (errors.isEmpty()) {

            FacadeService facadeService = FacadeServiceImpl.getInstance();
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.id = id;
            filmDTO.title = title;
            filmDTO.releaseYear = year;
            filmDTO.genreId = reqGender;
            filmDTO.coverName = getFileCover(request);
            if (request.getParameter("action").equals("create")) {
                facadeService.insert(filmDTO);
            } else if (request.getParameter("action").equals("edit")) {
                facadeService.update(filmDTO);
            }


            try {
                response.sendRedirect("FilmListServlet");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            defaultFilm.put("title", title);
            defaultFilm.put("year", yearStr);
            defaultFilm.put("genre", String.valueOf(reqGender));
            request.setAttribute(CustomTags.ERRORS, errors);
            request.setAttribute(CustomTags.FILM, defaultFilm);
            doGet(request, response);
            errors.clear();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        setPageValues(request);
        List<Genre> genres;
        FacadeService facadeService = FacadeServiceImpl.getInstance();
        genres = facadeService.getGenres();
        request.setAttribute(CustomTags.GENRES, genres);
        try {
            request.getRequestDispatcher("AddFilm.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void setPageValues(HttpServletRequest request) {
        String action = request.getParameter("action");
        String headerTitle = action.equals("create") ? "Create new Film" : "Update Film";
        String buttonName = action.equals("create") ? "Add Film" : "Update";

        Map<String, String> actionValues = new HashMap<>();
        actionValues.put("act", action);
        actionValues.put("headerTitle", headerTitle);
        actionValues.put("buttonName", buttonName);

        request.setAttribute("values", actionValues);

        if (action.equals("edit")) {
            List<Genre> genres;
            int id = 0;
            if (request.getParameter("id") != null) {
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    e.printStackTrace();
                    id = 0;
                }
                FilmDTO filmDTO;
                FacadeService facadeService = FacadeServiceImpl.getInstance();
                filmDTO = facadeService.findById(id);
                request.setAttribute(CustomTags.FILM, filmDTO);

            }

            FacadeService facadeService = FacadeServiceImpl.getInstance();
            genres = facadeService.getGenres();

            request.setAttribute(CustomTags.GENRES, genres);
            request.setAttribute(CustomTags.ID, id);
        }
    }


    private String getFileCover(HttpServletRequest request) throws IOException, ServletException {
        final String PATH="/Applications/XAMPP/xamppfiles/htdocs/img";
        final Part FILE_PART = request.getPart("film_cover");
        final String FILE_NAME = getFileName(FILE_PART);
        File img = new File(PATH+"/"+FILE_NAME);
        img.setReadable(true,false);
        try (FileOutputStream out = new FileOutputStream(img);
             InputStream fileContent = FILE_PART.getInputStream()) {

            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return FILE_NAME;
    }


    private String getFileName(final Part PART) {
        for (String content : PART.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return null;
    }



}
