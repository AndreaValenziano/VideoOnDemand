package com.videoondemand.model;

import com.dao.dto.FilmDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AndreaValenziano on 28/11/17.
 */
public class Database {
    private static ArrayList<Film> films = new ArrayList<>(Arrays.asList(
            new Film("Inception", 0, 2010),
            new Film("The Godfather", 0, 1972),
            new Film("The Lord of the Rings: The Fellowship of the Ring", 0, 2001)));

    public static List<FilmDTO> getFilms() {
        return null; //must return filmDTO
    }
}


