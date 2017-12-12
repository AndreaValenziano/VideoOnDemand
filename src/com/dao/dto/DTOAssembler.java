package com.dao.dto;

import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreaValenziano on 12/12/17.
 */
public class DTOAssembler {

    public static List<FilmDTO> createListFilmDTO(List<Film> films, List<Genre> genres) {

        List<FilmDTO> filmsDTO = new ArrayList<>();

        for (Film film : films) {
            filmsDTO.add(createFilmDTO(film, genres.get(film.getGenre() - 1)));
        }
        return filmsDTO;
    }

    public static FilmDTO createFilmDTO(Film film, Genre genre) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.title = film.getTitle();
        filmDTO.releaseYear = film.getReleaseYear();
        filmDTO.id = film.getId();
        filmDTO.setGenre(genre);
        filmDTO.genreId = genre.getId();
        return filmDTO;
    }


    public static FilmDTO getFilmDTO(Film film) {
        return new FilmDTO(film);

    }


    public static Film getFilm(FilmDTO filmDTO) {
        Film film = new Film(filmDTO.title, filmDTO.genreId, filmDTO.releaseYear, filmDTO.coverName);
        film.setId(filmDTO.id);
        return film;
    }


}
