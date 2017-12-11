package com.videoondemand.model;

/**
 * Created by AndreaValenziano on 05/12/17.
 */
public class Genre {

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private int id;
    private String name, description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
