package com.dao.memory;

import com.dao.FilmDAO;
import com.dao.dto.FilmDTO;
import com.videoondemand.model.Database;
import com.videoondemand.model.Film;

import java.util.List;

public class FilmMemoryDAO implements FilmDAO {




    private static FilmMemoryDAO instance;

    public static FilmMemoryDAO getInstance() {
        if (instance == null) {
            instance = new FilmMemoryDAO();
        }
        return instance;
    }

    @Override
    public void insert(FilmDTO film) {
        Database.getFilms().add(film);

    }

    @Override
    public void update(FilmDTO film) {

    }

    @Override
    public void delete(FilmDTO film) {

    }

    @Override
    public FilmDTO findById(int id) {
        return null;
    }

    @Override
    public List<FilmDTO> findAll() {
        return Database.getFilms();
    }
}
