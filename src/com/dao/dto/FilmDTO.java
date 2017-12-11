package com.dao.dto;

import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public class FilmDTO  {
    private Genre genre;
    private Film film;

    public FilmDTO(Film film) {
        this.film = film;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
