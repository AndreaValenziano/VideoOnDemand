package com.facade;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.UserDAO;
import com.dao.dto.DTOAssembler;
import com.dao.dto.FilmDTO;
import com.dao.dto.UserDTO;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;


import java.util.List;

/**
 * Created by AndreaValenziano on 12/12/17.
 */
public class FacadeServiceImpl implements FacadeService {

    private FacadeServiceImpl() {

    }

    private static FacadeServiceImpl instance;

    public static FacadeServiceImpl getInstance() {

        if (instance == null) {
            instance = new FacadeServiceImpl();
        }
        return instance;
    }


    @Override
    public List<FilmDTO> getFilms() {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        GenreDAO genreDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getGenreDAO();
        return DTOAssembler.createListFilmDTO(filmDAO.findAll(), genreDAO.findAll());
    }

    @Override
    public List<Genre> getGenres() {
        GenreDAO genreDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getGenreDAO();
        return genreDAO.findAll();
    }

    @Override
    public void insert(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        filmDAO.insert(DTOAssembler.getFilm(film));
    }

    @Override
    public FilmDTO findFilmById(int id) {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        return DTOAssembler.getFilmDTO(filmDAO.findById(id));
    }

    @Override
    public void update(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        filmDAO.update(DTOAssembler.getFilm(film));
    }

    @Override
    public void delete(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getFilmDAO();
        filmDAO.delete(DTOAssembler.getFilm(film));
    }

    @Override
    public List<UserDTO> getUsers() {
        UserDAO userDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getUserDAO();
        return DTOAssembler.createListUserDTO(userDAO.findAll());
    }

    @Override
    public void insert(UserDTO userDTO) {
        UserDAO userDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getUserDAO();
        userDAO.insert(DTOAssembler.getUser(userDTO));

    }

    @Override
    public UserDTO findUserById(int id) {
        UserDAO userDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getUserDAO();
        return DTOAssembler.getUserDTO(userDAO.findById(id));
    }

    @Override
    public void update(UserDTO userDTO) {
        UserDAO userDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getUserDAO();
        userDAO.update(DTOAssembler.getUser(userDTO));

    }

    @Override
    public void delete(UserDTO userDTO) {
        UserDAO userDAO = FactoryDAO.getDAOFactory(FactoryDAO.TypeDAOFactory.DB).getUserDAO();
        userDAO.delete(DTOAssembler.getUser(userDTO));

    }
}
