package com.videoondemand.control;

import com.dao.dto.UserDTO;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by AndreaValenziano on 18/12/17.
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username =  request.getParameter("username");
        String password =  request.getParameter("password");

        FacadeService facadeService = FacadeServiceImpl.getInstance();

        UserDTO userDTO = facadeService.getUsers().stream().filter(u -> u.username.equals(username) && u.password.equals(password)).findFirst().orElse(null);

        if(userDTO==null){
            request.setAttribute("noUser","User not found");
            doGet(request,response);
        }else{
            session.setAttribute("user",userDTO);
           response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String noUser = request.getAttribute("noUser")!=null ? (String) request.getAttribute("noUser"):"";
       request.getRequestDispatcher("Login.jsp").forward(request,response);

    }
}
