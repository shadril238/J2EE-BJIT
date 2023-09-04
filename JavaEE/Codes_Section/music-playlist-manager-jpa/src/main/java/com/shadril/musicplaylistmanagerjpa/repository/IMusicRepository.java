package com.shadril.musicplaylistmanagerjpa.repository;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMusicRepository extends JpaRepository<Music, Integer> {
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Music m WHERE m.title = ?1")
    boolean musicAlreadyExistByTitle(String title);
}
