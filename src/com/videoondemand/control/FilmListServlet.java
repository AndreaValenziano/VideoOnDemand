package com.videoondemand.control;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.dto.FilmDTO;
import com.dao.memory.FilmMemoryDAO;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;
import com.videoondemand.utils.CustomTags;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
@WebServlet("/FilmListServlet")
public class FilmListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        List<FilmDTO> filmDTOS = filmDAO.findAll();

        GenreDAO genreDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getGenreDAO();

        for (FilmDTO film : filmDTOS) {
            film.setGenre(genreDAO.findById(film.getFilm().getGenre()));
        }

        request.setAttribute(CustomTags.FILM_LIST, filmDTOS);
        request.getRequestDispatcher("FilmList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}

