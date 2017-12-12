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
 * Created by AndreaValenziano on 28/11/17.
 */
@WebServlet("/FormAddFilmServlet")
public class FormAddFilmServlet extends HttpServlet {
    private static final String YEAR_ERROR = "Invalid year";
    private static final String YEAR_RANGE_ERROR = "The year must be between 1920 and ";
    private static final String TITLE_ERROR = "Title length is shorter than 2 character";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        HashMap<String, String> defaultFilm = new HashMap<>();
        defaultFilm.put("title", "");
        defaultFilm.put("gender", "");
        defaultFilm.put("year", "");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        title = title != null ? title.trim() : "";


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
            filmDTO.title = title;
            filmDTO.releaseYear = year;
            filmDTO.genreId = reqGender;
            facadeService.insert(filmDTO);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
}
