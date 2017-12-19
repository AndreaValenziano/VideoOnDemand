package com.videoondemand.control;

import com.dao.dto.FilmDTO;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
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

        FacadeService facadeService = FacadeServiceImpl.getInstance();
        List<FilmDTO> films = facadeService.getFilms();
        for(FilmDTO film: films){
            film.setGenre(facadeService.getGenres().get(film.genreId-1));
        }

        request.setAttribute(CustomTags.FILM_LIST, films);
        request.getRequestDispatcher("Gallery.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}

