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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AndreaValenziano on 06/12/17.
 */
@WebServlet("/UpdateFilm")
public class UpdateFilmServlet extends HttpServlet {
    private static final String YEAR_ERROR = "Invalid year";
    private static final String YEAR_RANGE_ERROR = "The year must be between 1920 and ";
    private static final String TITLE_ERROR = "Title length is shorter than 2 character";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String title, yearStr;
        int reqGender;
        int id;
        List<String> errors = new ArrayList<>();
        HashMap<String, String> defaultFilm = new HashMap<>();
        defaultFilm.put("title", "");
        defaultFilm.put("gender", "");
        defaultFilm.put("year", "");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        title = request.getParameter("title");
        title = title != null ? title.trim() : "";

        id = Integer.parseInt(request.getParameter("id"));

        yearStr = request.getParameter("year");
        yearStr = yearStr != null ? yearStr.trim() : "";
        int year = -1;

        try {
            reqGender = Integer.parseInt(request.getParameter("genre"));
        } catch (Exception e) {
            e.printStackTrace();
            reqGender = 0;
        }


        try {
            year = Integer.parseInt(yearStr);
            if (year < 1920 || year > LocalDate.now().getYear()) {
                defaultFilm.put("year", String.valueOf(year));
                errors.add(YEAR_RANGE_ERROR + LocalDate.now().getYear());
            }
        } catch (NumberFormatException e) {
            defaultFilm.put("year", yearStr);
            errors.add(YEAR_ERROR);
        }

        if (title.length() < 2) {
            defaultFilm.put("title", title);

            errors.add(TITLE_ERROR);
        }

        if (errors.isEmpty()) {
            FacadeService facadeService = FacadeServiceImpl.getInstance();
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.id=id;
            filmDTO.title=title;
            filmDTO.releaseYear=year;
            filmDTO.genreId=reqGender;


            filmDTO.setGenre(facadeService.getGenres().get(reqGender-1));
            facadeService.update(filmDTO);

            try {
                request.getRequestDispatcher("FilmListServlet").forward(request,response);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            defaultFilm.put("genre", String.valueOf(reqGender));
            request.setAttribute(CustomTags.ERRORS, errors);
            request.setAttribute(CustomTags.FILM, defaultFilm);
            doGet(request, response);
            errors.clear();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres;
        int id = 0;
        if (request.getParameter("id") != null) {

            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                e.printStackTrace();
                id = 0;
            }
            Film film;
            FacadeService facadeService = FacadeServiceImpl.getInstance();
            try {

            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Film Not Found");
            }
            film = facadeService.findById(id).getFilm();

            request.setAttribute(CustomTags.FILM, film);

        }

        FacadeService facadeService = FacadeServiceImpl.getInstance();
        genres = facadeService.getGenres();

        request.setAttribute(CustomTags.GENRES, genres);
        request.setAttribute(CustomTags.ID, id);
        request.getRequestDispatcher("UpdateFilm.jsp").forward(request, response);


    }
}
