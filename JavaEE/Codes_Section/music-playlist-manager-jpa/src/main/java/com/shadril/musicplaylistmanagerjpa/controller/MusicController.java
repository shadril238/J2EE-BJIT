package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/api/music/getall")
    public ResponseEntity<List<Music>> getAllMusics() {
        List<Music> musicList = musicService.getAllMusics();
        return new ResponseEntity<>(musicList, HttpStatus.OK);
    }

    @GetMapping("/api/music/get/{id}")
    public ResponseEntity<Music> getAllMusics(@PathVariable Integer id) {
        Optional<Music> music = musicService.getMusicById(id);
        return new ResponseEntity<>(music.get(), HttpStatus.OK);
    }

    @PostMapping("/api/music/add")
    public ResponseEntity<Void> addMusic(@RequestBody Music music) {
        musicService.addMusic(music);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
