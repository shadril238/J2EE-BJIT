package com.shadril.musicplaylistmanagerjpa.service;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.repository.IMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    @Autowired
    private IMusicRepository musicRepository;

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Optional<Music> getMusicById(Integer id) {
        return musicRepository.findById(id);
    }

    public void addMusic(Music music) {
        musicRepository.save(music);
    }

}
