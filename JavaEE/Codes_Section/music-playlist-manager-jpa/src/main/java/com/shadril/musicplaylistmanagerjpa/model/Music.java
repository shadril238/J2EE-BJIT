package com.shadril.musicplaylistmanagerjpa.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;

@Entity
@Component
@Table(name = "musics")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 50)
    private String artist;
    @Column(nullable = false, length = 50)
    private String album;
    @Column(nullable = false, length = 50)
    private String genre;
    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "musics")
    private List<Playlist> playlists;

    public Music() {
    }

    public Music(Integer id, String title, String artist, String album, String genre, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
