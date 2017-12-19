package com.dao.dto;

import com.videoondemand.model.Film;
import com.videoondemand.model.Genre;
import com.videoondemand.model.User;

import java.util.ArrayList;
import java.util.HashMap;
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
        filmDTO.coverName = film.getCoverName();
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

    public static User getUser(UserDTO userDTO) {
        User user = new User(userDTO.username, userDTO.password, userDTO.role);
        user.setId(userDTO.id);
        return user;
    }

    public static UserDTO getUserDTO(User user) {
        return new UserDTO(user);
    }


    public static List<UserDTO> createListUserDTO(List<User> users) {

        List<UserDTO> usersDTO = new ArrayList<>();

        for (User user : users) {
            usersDTO.add(getUserDTO(user));
        }
        return usersDTO;
    }
}
