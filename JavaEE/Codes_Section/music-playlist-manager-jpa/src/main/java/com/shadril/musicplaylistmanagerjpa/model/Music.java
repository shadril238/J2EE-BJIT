package com.shadril.musicplaylistmanagerjpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

//    @ManyToMany(mappedBy = "musics")
//    @JsonBackReference
//    private List<Playlist> playlists;

}
