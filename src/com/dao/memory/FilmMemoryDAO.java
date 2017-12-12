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
    public void insert(Film film) {
        Database.getFilms().add(film);

    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void delete(Film film) {

    }

    @Override
    public Film findById(int id) {
        return null;
    }

    @Override
    public List<Film> findAll() {
        return Database.getFilms();
    }
}
