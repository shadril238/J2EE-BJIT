package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Music;
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
@RequestMapping("/api/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MusicService musicService;

    @GetMapping("/getall")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlistList = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlistList, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Integer id)
            throws PlaylistNotFoundException {
        Playlist playlist = playlistService.getPlaylistById(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<Playlist> getPlaylistByName(@PathVariable String name)
            throws PlaylistNotFoundException {
        Playlist playlist = playlistService.getPlaylistByName(name);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPlaylist(@RequestBody Playlist playlist)
            throws PlaylistAlreadyExistException, MusicNotFoundException {
        playlistService.addPlaylist(playlist);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePlaylist(@RequestBody Playlist playlist)
            throws PlaylistNotFoundException, PlaylistAlreadyExistException{
        playlistService.updatePlaylist(playlist);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Integer id)
            throws PlaylistNotFoundException{
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/music/add/{playlistId}/{musicId}")
    public ResponseEntity<String> addMusicToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId)
            throws PlaylistNotFoundException, MusicAlreadyExistException, MusicNotFoundException {
        playlistService.addMusicToPlaylist(playlistId, musicId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/music/remove/{playlistId}/{musicId}")
    public ResponseEntity<String> removeMusicFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer musicId)
        throws PlaylistNotFoundException, MusicNotFoundException {
        playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
