<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dao.memory.FilmMemoryDAO" %>
<%@ page import="com.videoondemand.model.Film" %>
<%@ page import="com.videoondemand.utils.CustomTags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.dto.FilmDTO" %><%--
  Created by IntelliJ IDEA.
  User: AndreaValenziano
  Date: 28/11/17
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>

<%
    List<FilmDTO> filmList = (List<FilmDTO>) request.getAttribute(CustomTags.FILM_LIST);
%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Film Gallery</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <% for (FilmDTO film : filmList) {%>

                    <div class="col-md-4">
                        <div class="thumbnail">
                            <a href="<%= film.coverName %>">
                                <img src="<%= film.coverName%>" onerror="imgError(this);" style="width:100%" >
                            </a>
                            <div class="caption">
                                <p id="title"><%= film.title%>
                                </p>
                                <p id="year"><%= film.releaseYear%>
                                </p>
                                <p id="genre"><%= film.getGenre().getName()%>
                                </p>
                                <a class="btn" href="FormAddFilmServlet?id=<%= film.id %>&action=edit"><i
                                        class="fa fa-pencil-square-o"
                                        aria-hidden="true"></i></a>
                                <a class="btn" href="DeleteFilm?id=<%= film.id %>"><i
                                        class="fa fa-trash"
                                        aria-hidden="true"></i></a>
                            </div>

                        </div>
                    </div>
                    <%}%>

                    <%--<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Gender</th>
                            <th>Year</th>
                            <th>Cover</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% int i = 0;
                            String trClass = "odd gradeA";
                            for (FilmDTO film : filmList) {
                                if (film != null) {
                                    if (i % 2 == 0) {
                                        trClass = "odd gradeA";
                                    } else {
                                        trClass = "even gradeC";
                                    }
                        %>
                        <tr class="<%= trClass %>">
                            <td><%= film.title %>
                            </td>
                            <td><%= film.getGenre().getName() %>
                            </td>
                            <td><%= film.releaseYear %>
                            </td>
                            <td>
                                Vedi cover <!--TODO: Mostrare immagine presa dal db -->
                            </td>
                            <td style="text-align: center;"><a class="btn" href="FormAddFilmServlet?id=<%= film.id %>&action=edit"><i class="fa fa-pencil-square-o"
                                                                                                                                      aria-hidden="true"></i></a>
                                <a class="btn" href="DeleteFilm?id=<%= film.id %>"><i
                                        class="fa fa-trash"
                                        aria-hidden="true"></i></a></td>
                        </tr>
                        <% i++;
                        }
                        } %>

                        </tbody>
                    </table>
                    <!-- /.table-responsive -->--%>

                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->

</div>
<!-- /#page-wrapper -->



