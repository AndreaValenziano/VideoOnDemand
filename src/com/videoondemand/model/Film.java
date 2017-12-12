package com.videoondemand.model;

import java.time.LocalDate;

public class Film {
    private int id;
    private String title;
    private int genre;
    private int releaseYear;
    private String director, cast, description;
    private int duration;
    private LocalDate creationDate;
    private String coverName;

    public Film(String title, int genre, int releaseYear, String director, String cast, String description, int duration, LocalDate creationDate, String coverName) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.cast = cast;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
        this.coverName = coverName;
    }


    public Film(String title, int genre, int releaseYear, String coverName) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.coverName = coverName;
        this.creationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCoverName() {
        return coverName;
    }
}
