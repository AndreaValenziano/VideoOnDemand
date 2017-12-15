<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.videoondemand.model.Film" %>
<%@ page import="java.time.LocalDate" %>

<%@ page import="com.videoondemand.utils.CustomTags" %>
<%@ page import="java.util.HashMap" %>

<%@ page import="com.videoondemand.model.Genre" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.dao.dto.FilmDTO" %><%--
  Created by IntelliJ IDEA.
  User: AndreaValenziano
  Date: 28/11/17
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>


<%
    String title = "", year = "";
    int genre = 0, id = 0;
    List<Genre> genres;
    genres = (List<Genre>) request.getAttribute(CustomTags.GENRES);
    FilmDTO film;
    Map<String, String> values = (Map<String, String>) request.getAttribute("values");
    String action = values.get("act");
    String headerTitle = values.get("headerTitle");
    String buttonName = values.get("buttonName");


    if (request.getAttribute(CustomTags.FILM) instanceof FilmDTO) {
        if (request.getParameter("action").toLowerCase().equals("edit")) {
            film = (FilmDTO) request.getAttribute(CustomTags.FILM);
            title = film.title;
            year = String.valueOf(film.releaseYear);
            id = (film.id);
            genre = film.genreId;
        }
    } else if (request.getAttribute(CustomTags.FILM) instanceof HashMap) {
        HashMap<String, String> defaultFilm = (HashMap<String, String>) request.getAttribute(CustomTags.FILM);
        title = defaultFilm.get("title");
        title = title != null ? title.trim() : "";
        genre = Integer.parseInt(defaultFilm.get("genre"));
        year = defaultFilm.get("year");

    }

%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><%= headerTitle%>
            </h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Video On Demand
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form action="FormAddFilmServlet?action=<%= action %>" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" value='<%= id%>' name='id' hidden="true">
                                    <label>Title</label>
                                    <input type="text" name='title' value='<%= title%>' class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Genre</label>
                                    <select name="genre" class="form-control">
                                        <%
                                            for (Genre g : genres) {
                                                String selected = g.getId() == genre ? " selected " : "";

                                        %>
                                        <option value='<%=g.getId()%>' <%=selected%>>
                                            <%=g.getName()%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Release year</label>
                                    <input type="text" class="form-control" name='year' value='<%=year%>'>
                                </div>
                                <div class="form-group">
                                    <label>Cover:</label>
                                    <input type="file" class="form-control" name="film_cover">
                                </div>
                                <%
                                    List<String> errors = (List) request.getAttribute(CustomTags.ERRORS);
                                    if (errors != null) {
                                        for (String error : errors) {
                                %>
                                <p style='color: red;'><%=error%>
                                </p>
                                <%
                                        }
                                        errors.clear();
                                    }
                                %>
                                <button type="submit" class="btn btn-default"><%= buttonName%>
                                </button>

                            </form>
                        </div>

                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>


