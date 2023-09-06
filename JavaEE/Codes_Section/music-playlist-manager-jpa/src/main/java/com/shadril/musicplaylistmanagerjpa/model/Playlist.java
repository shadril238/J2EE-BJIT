package com.shadril.musicplaylistmanagerjpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "music_playlists",
            joinColumns = {
                    @JoinColumn(name = "playlist_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "music_id")
            })
    //@JsonManagedReference
    private List<Music> musics;
}
