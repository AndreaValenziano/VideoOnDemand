package com.dao.memory;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
public class MemoryDAOFactory implements FactoryDAO {
    private MemoryDAOFactory(){

    }

    private static MemoryDAOFactory instance;

    public static MemoryDAOFactory getInstance(){
        if ( instance == null){
            instance = new MemoryDAOFactory();
        }
        return instance;
    }


    @Override
    public FilmDAO getFilmDAO() {
        return FilmMemoryDAO.getInstance();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return null; //create GenreMemoryDAO
    }
}
