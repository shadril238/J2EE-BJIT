package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/api/playlist/getall")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlistList = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlistList, HttpStatus.OK);
    }

    @GetMapping("/api/playlist/get/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Integer id) {
        Optional<Playlist> playlist = playlistService.getPlaylistById(id);
        return new ResponseEntity<>(playlist.get(), HttpStatus.OK);
    }

    @PostMapping("/api/playlist/add")
    public ResponseEntity<Void> addPlaylist(@RequestBody Playlist playlist) {
        playlistService.addPlaylist(playlist);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/api/playlist/update")
    public ResponseEntity<Void> updatePlaylist(@RequestBody Playlist playlist) {
        playlistService.updatePlaylist(playlist);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/api/playlist/delete/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer id) {
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
