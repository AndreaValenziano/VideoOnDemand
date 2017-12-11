package com.dao;

import com.videoondemand.model.Genre;

import java.util.List;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public interface GenreDAO  {

    void insert(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
    Genre findById(int id);
    List<Genre> findAll();
}
