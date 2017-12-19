package com.dao.jdbc;


import com.dao.UserDAO;
import com.videoondemand.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreaValenziano on 18/12/17.
 */
public class UserJDBCDAO implements UserDAO {

    private static UserJDBCDAO instance;

    public static UserJDBCDAO getInstance() {
        if (instance == null) {
            instance = new UserJDBCDAO();
        }
        return instance;
    }


    public void insert(User user) {
        String insertUserQuery = "INSERT INTO user (username, password, role) VALUES (?, ? ,?)";
        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }


    public User findById(int userId) {


        try (
                Connection connection = JDBCDAOFactory.getConnection();
                PreparedStatement ps = createPreparedStatement(connection, userId);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                User user = new User(username, password, role);
                user.setId(id);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        String findAllQuery = "SELECT * FROM user";
        try (Connection connection = JDBCDAOFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(findAllQuery);
             ResultSet rs = ps.executeQuery()) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                User user = new User(username, password, role);
                user.setId(id);
                users.add(user);
            }

            return users;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private PreparedStatement createPreparedStatement(Connection con, int id) throws SQLException {
        String findFilmQuery = "SELECT * FROM user WHERE user.id = ?";
        PreparedStatement ps = con.prepareStatement(findFilmQuery);
        ps.setInt(1, id);
        return ps;
    }

}
