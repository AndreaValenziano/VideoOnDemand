package com.dao;

import com.dao.jdbc.JDBCDAOFactory;
import com.dao.memory.FilmMemoryDAO;
import com.dao.memory.MemoryDAOFactory;
import com.videoondemand.model.Genre;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
public interface FactoryDAO {
    public enum TypeDAOFactory {
        MEMORY, DB, XML
    }

    static FactoryDAO getDAOFactory(TypeDAOFactory type) {
        switch (type) {
            case MEMORY:
                return MemoryDAOFactory.getInstance();
            case DB:
                return JDBCDAOFactory.getInstance();
        }
        return null;
    }

    FilmDAO getFilmDAO();
    GenreDAO getGenreDAO();
    UserDAO getUserDAO();
}
