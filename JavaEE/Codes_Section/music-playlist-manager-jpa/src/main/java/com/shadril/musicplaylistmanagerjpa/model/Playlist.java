package com.shadril.musicplaylistmanagerjpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity

@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false)
    private LocalDate createdDate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "music_playlists", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "music_id"))
    private List<Music> musicList;

    public Playlist() {
    }

    public Playlist(Integer id, String name, String description, LocalDate createdDate, List<Music> musicList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.musicList = musicList;
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