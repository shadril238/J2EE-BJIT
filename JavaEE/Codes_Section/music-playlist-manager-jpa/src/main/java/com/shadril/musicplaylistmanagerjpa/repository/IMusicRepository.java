package com.shadril.musicplaylistmanagerjpa.repository;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface IMusicRepository extends JpaRepository<Music, Integer> {
    Optional<Music> findMusicByTitle(String title);
}

