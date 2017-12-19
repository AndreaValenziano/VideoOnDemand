package com.facade;

import com.dao.dto.FilmDTO;
import com.dao.dto.UserDTO;
import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;
import java.util.List;

/**
 * Created by AndreaValenziano on 12/12/17.
 */
public interface FacadeService {
    List<FilmDTO> getFilms();
    List<Genre> getGenres();
    List<UserDTO> getUsers();

    void insert(FilmDTO film);

    FilmDTO findFilmById(int id);

    void update(FilmDTO film);

    void delete(FilmDTO film);

    void insert(UserDTO userDTO);

    UserDTO findUserById(int id);

    void update(UserDTO userDTO);

    void delete(UserDTO userDTO);
}
