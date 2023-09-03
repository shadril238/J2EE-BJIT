package com.shadril.musicplaylistmanagerjpa.service;

import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private IPlaylistRepository playlistRepository;

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> getPlaylistById(Integer id) {
        return playlistRepository.findById(id);
    }

    public void addPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void updatePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void deletePlaylist(Integer id) {
        playlistRepository.deleteById(id);
    }
}
