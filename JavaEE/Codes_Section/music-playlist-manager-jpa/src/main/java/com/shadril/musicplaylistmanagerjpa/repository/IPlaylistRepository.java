package com.shadril.musicplaylistmanagerjpa.repository;

import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPlaylistRepository extends JpaRepository<Playlist, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Playlist p JOIN p.musics m WHERE p.name = ? AND m.title = ?")
    Boolean musicAlreadyExistInPlaylist(String playlistName, String musicTitle);
}
