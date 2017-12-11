package com.dao.jdbc;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.memory.FilmMemoryDAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public class JDBCDAOFactory implements FactoryDAO {

    private static JDBCDAOFactory instance;

    public static JDBCDAOFactory getInstance() {
        if (instance == null) {
            instance = new JDBCDAOFactory();
        }
        return instance;
    }

    private JDBCDAOFactory(){

    }


    @Override
    public FilmDAO getFilmDAO() {
        return FilmJDBCDAO.getInstance();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return GenreJDBCDAO.getInstance();
    }

    public static Connection getConnection() {

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/videoOnDemand");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
