package com.dao.jdbc;

import com.dao.GenreDAO;
import com.videoondemand.model.Genre;

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
public class GenreJDBCDAO implements GenreDAO {

    private static GenreJDBCDAO instance;

    public static GenreJDBCDAO getInstance() {
        if (instance == null) {
            instance = new GenreJDBCDAO();
        }
        return instance;
    }

    @Override
    public void insert(Genre genre) {

        String insertGenreQuery = "INSERT INTO genre ( name, description) VALUES (" +
                "?," + //name
                "?)"; //description

        try (Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertGenreQuery)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setString(2, genre.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Genre genre) {

        String updateGenreQuery = "UPDATE genre SET name = ?, description=? WHERE id=?";


        try (Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateGenreQuery)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setString(2, genre.getDescription());
            preparedStatement.setInt(3, genre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Genre genre) {
        String deleteGenreQuery = "DELETE FROM genre WHERE id = ?";
        try (Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteGenreQuery);) {
            preparedStatement.setInt(1, genre.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Genre findById(int genreId) {
        try (Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement ps = createPreparedStatement(connection, genreId);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                Genre genre = new Genre(name, description);
                genre.setId(id);
                return genre;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Genre> findAll() {
        String findAllQuery = "SELECT * FROM genre";
        try (Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(findAllQuery);
             ResultSet rs = ps.executeQuery()) {
            List<Genre> genres = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Genre genre = new Genre(name, description);
                genre.setId(id);
                genres.add(genre);
            }

            return genres;


        }catch (SQLException e){
            e.printStackTrace();
        }


        return null;
    }

    private PreparedStatement createPreparedStatement(Connection con, int id) throws SQLException {
        String findGenreQuery = "SELECT * FROM genre WHERE id= ?";
        PreparedStatement ps = con.prepareStatement(findGenreQuery);
        ps.setInt(1, id);
        return ps;
    }
}


