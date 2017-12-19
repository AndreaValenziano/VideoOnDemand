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
</nav>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Login
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
                            <form action="Login" method="POST">
                                <div class="form-group">
                                    <!-- <input type="text" value='' name='id' hidden="true">-->
                                    <label>Username
                                        <input type="email" name="username" class="form-control" required></label>
                                </div>
                                <div class="form-group">
                                    <label>Password
                                        <input type="password" name="password" class="form-control" required></label>
                                </div>
                                <% String err = request.getAttribute("noUser") == null ? "" : (String) request.getAttribute("noUser"); %>
                                <p style="color:red;"><%= err %>
                                </p>
                                <button type="submit" class="btn btn-default">LOGIN
                                </button>
                                Non sei ancora iscritto? <a href="Registration">Registrati!</a>

                            </form>
                        </div>

                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>


