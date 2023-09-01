package com.shadril.musicplaylistmanagerjpa.model;

import java.time.LocalDate;

public class Playlist {
    private Integer id;
    private String name;
    private String description;
    private LocalDate createdDate;

    public Playlist() {
    }

    public Playlist(Integer id, String name, String description, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
