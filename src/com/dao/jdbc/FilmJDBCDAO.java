package com.dao.jdbc;

import com.dao.FilmDAO;
import com.dao.dto.FilmDTO;
import com.videoondemand.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public class FilmJDBCDAO implements FilmDAO {

    private static FilmJDBCDAO instance;

    public static FilmJDBCDAO getInstance() {
        if (instance == null) {
            instance = new FilmJDBCDAO();
        }
        return instance;
    }

    @Override
    public void insert(Film film) {
        String insertFilmQuery = "INSERT INTO film " +
                "(title,genre,year,director,cast,duration,description,file_cover_name) VALUES" +
                "(?," + //title
                "?," +  //genre
                "?," +  //year
                "?," +  //director
                "?," +  //cast
                "?," +  //duration
                "?," +  //description
                "?)";   //covername


        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertFilmQuery)) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setInt(2, film.getGenre());
            preparedStatement.setInt(3, film.getReleaseYear());
            preparedStatement.setString(4, film.getDirector());
            preparedStatement.setString(5, film.getCast());
            preparedStatement.setInt(6, film.getDuration());
            preparedStatement.setString(7, film.getDescription());
            preparedStatement.setString(8, film.getCoverName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Film film) {

        String updateFilmQuery = "UPDATE film SET title = ?, genre = ?, year = ?, director = ?, cast = ?, duration = ?, description = ?, file_cover_name = ? WHERE id = ?";
        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateFilmQuery)) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setInt(2, film.getGenre());
            preparedStatement.setInt(3, film.getReleaseYear());
            preparedStatement.setString(4, film.getDirector());
            preparedStatement.setString(5, film.getCast());
            preparedStatement.setInt(6, film.getDuration());
            preparedStatement.setString(7, film.getDescription());
            if (!film.getCoverName().equals("")) {
                preparedStatement.setString(8, film.getCoverName());
            }
            preparedStatement.setInt(9, film.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Film film) {
        String deleteFilmQuery = "DELETE FROM film WHERE id=?";
        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteFilmQuery)) {
            preparedStatement.setInt(1, film.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Film findById(int filmId) {
        final String PATH = "http://localhost/img/";
        try (
                Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement ps = createPreparedStatement(connection, filmId);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int genre = rs.getInt("genre");
                int year = rs.getInt("year");
                String director = rs.getString("director");
                String cast = rs.getString("cast");
                int duration = rs.getInt("duration");
                String description = rs.getString("description");
                LocalDate date = rs.getDate("creation_date").toLocalDate();
                String coverName = rs.getString("file_cover_name");
                Film film = new Film(title, genre, year, director, cast, description, duration, date, PATH + coverName);
                film.setId(id);
                return film;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Film> findAll() {
        final String PATH = "http://localhost/img/";
        String findAllQuery = "SELECT * FROM film";
        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(findAllQuery);
             ResultSet rs = ps.executeQuery()) {
            List<Film> films = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int genre = rs.getInt("genre");
                int year = rs.getInt("year");
                String director = rs.getString("director");
                String cast = rs.getString("cast");
                int duration = rs.getInt("duration");
                String description = rs.getString("description");
                LocalDate date = rs.getDate("creation_date").toLocalDate();
                String coverName = rs.getString("file_cover_name");

                Film film = new Film(title, genre, year, director, cast, description, duration, date, PATH + coverName);
                film.setId(id);
                films.add(film);
            }

            return films;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    private PreparedStatement createPreparedStatement(Connection con, int id) throws SQLException {
        String findFilmQuery = "SELECT * FROM film WHERE film.id = ?";
        PreparedStatement ps = con.prepareStatement(findFilmQuery);
        ps.setInt(1, id);
        return ps;
    }
}
