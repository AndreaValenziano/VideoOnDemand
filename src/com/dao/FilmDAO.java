package com.dao;

import com.dao.dto.FilmDTO;
import com.videoondemand.model.Film;

import java.util.List;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
public interface  FilmDAO {

    void insert(FilmDTO film);
    void update(FilmDTO film);
    void delete(FilmDTO film);
    FilmDTO findById(int id);
    List<FilmDTO> findAll();

}
