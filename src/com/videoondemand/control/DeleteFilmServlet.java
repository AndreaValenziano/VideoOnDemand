package com.videoondemand.control;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by AndreaValenziano on 06/12/17.
 */
@WebServlet("/DeleteFilm")
public class DeleteFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id ;
       try {
           id = Integer.parseInt(request.getParameter("id"));
       }catch (Exception e){
           e.printStackTrace();
           id =0;
       }

        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
       try {
           filmDAO.delete(filmDAO.findById(id));
           response.sendRedirect("FilmListServlet");
       }catch (NullPointerException e){
           e.printStackTrace();
           System.out.println("Film Not Found");
       }


    }
}
