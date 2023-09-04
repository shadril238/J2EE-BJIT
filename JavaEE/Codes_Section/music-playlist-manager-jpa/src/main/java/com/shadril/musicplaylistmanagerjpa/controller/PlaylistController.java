package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.service.MusicService;
import com.shadril.musicplaylistmanagerjpa.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MusicService musicService;

    @GetMapping("/api/playlist/getall")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlistList = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlistList, HttpStatus.OK);
    }

    @GetMapping("/api/playlist/get/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Integer id) throws PlaylistNotFoundException {
        Playlist playlist = playlistService.getPlaylistById(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PostMapping("/api/playlist/add")
    public ResponseEntity<String> addPlaylist(@RequestBody Playlist playlist) {
        playlistService.addPlaylist(playlist);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/api/playlist/update")
    public ResponseEntity<String> updatePlaylist(@RequestBody Playlist playlist) {
        playlistService.updatePlaylist(playlist);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/api/playlist/delete/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Integer id) throws PlaylistNotFoundException{
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/api/playlist/remove-music/{playlist_id}/{music_id}")
    public ResponseEntity<String> removeMusicFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId) throws MusicNotFoundException, PlaylistNotFoundException {
        playlistService.removeMusicFromPlaylist(playlistId,  musicId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/api/playlist/add-music/{playlist_id}/{music_id}")
    public ResponseEntity<String> addMusicToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId) throws MusicNotFoundException, PlaylistNotFoundException {
        playlistService.addMusicToPlaylist(playlistId, musicId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
