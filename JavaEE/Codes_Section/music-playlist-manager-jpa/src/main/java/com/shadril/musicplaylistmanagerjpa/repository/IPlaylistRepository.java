package com.shadril.musicplaylistmanagerjpa.repository;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPlaylistRepository extends JpaRepository<Playlist, Integer> {
    Optional<Playlist> findPlaylistByName(String name);
}
