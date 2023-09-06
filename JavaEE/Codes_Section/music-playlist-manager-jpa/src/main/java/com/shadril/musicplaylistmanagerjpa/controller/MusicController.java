package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/getall")
    public ResponseEntity<List<Music>> getAllMusics() {
        List<Music> musicList = musicService.getAllMusics();
        return new ResponseEntity<>(musicList, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable("id") Integer id) throws MusicNotFoundException {
        Music music = musicService.getMusicById(id);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<Music> getMusicByTitle(@PathVariable("title") String title) throws MusicNotFoundException{
        Music music = musicService.getMusicByTitle(title);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMusic(@RequestBody Music music) throws MusicAlreadyExistException {
        musicService.addMusic(music);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMusic(@RequestBody Music music) throws MusicNotFoundException, MusicAlreadyExistException{
        musicService.updateMusic(music);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable Integer id) throws MusicNotFoundException{
        musicService.deleteMusic(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
