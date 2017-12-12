package com.dao.dto;

import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;

import java.time.LocalDate;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public class FilmDTO  {
    private Genre genre;
    private Film film;

    public int id;
    public String title;
    public int genreId;
    public int releaseYear;
    public String director, cast, description;
    public int duration;
    public LocalDate creationDate;
    public String coverName;


    public FilmDTO(){}


    public FilmDTO(Film film) {
        this.film = film;
        this.title = film.getTitle();
        this.id = film.getId();
        this.genreId = film.getGenre();
        this.releaseYear = film.getReleaseYear();
        this.cast= film.getCast();
        this.description = film.getDescription();
        this.director = film.getDirector();
        this.duration = film.getDuration();
        this.creationDate = film.getCreationDate();
        this.coverName = film.getCoverName();

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
