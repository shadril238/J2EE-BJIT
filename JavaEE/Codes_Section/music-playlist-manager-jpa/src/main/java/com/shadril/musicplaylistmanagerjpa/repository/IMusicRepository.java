package com.shadril.musicplaylistmanagerjpa.repository;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusicRepository extends JpaRepository<Music, Integer> {

}
