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
            <h1 class="page-header">Film List</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Gender</th>
                            <th>Year</th>
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
                            <td><%= film.getGenre().getName() %> <!--TODO: gestire il genere nella tabella -->
                            </td>
                            <td><%= film.releaseYear %>
                            </td>
                            <td style="text-align: center;"><a class="btn" href="UpdateFilm?id=<%= film.id %>"><i class="fa fa-pencil-square-o"
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
                    <!-- /.table-responsive -->

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

