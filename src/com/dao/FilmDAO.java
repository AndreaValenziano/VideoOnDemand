package com.dao;

import com.dao.dto.FilmDTO;
import com.videoondemand.model.Film;

import java.util.List;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
public interface  FilmDAO {

    void insert(Film film);
    void update(Film film);
    void delete(Film film);
    Film findById(int id);
    List<Film> findAll();

}
