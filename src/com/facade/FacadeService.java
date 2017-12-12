package com.facade;

import com.dao.dto.FilmDTO;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;
import java.util.List;

/**
 * Created by AndreaValenziano on 12/12/17.
 */
public interface FacadeService {
    List<FilmDTO> getFilms();
    List<Genre> getGenres();

    void insert(FilmDTO film);

    FilmDTO findById(int id);

    void update(FilmDTO film);

    void delete(FilmDTO film);

    FilmDTO createFilmDTO(String title, int reqGender, int year);
}
