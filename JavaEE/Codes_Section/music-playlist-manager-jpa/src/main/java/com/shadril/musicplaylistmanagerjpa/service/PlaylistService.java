package com.shadril.musicplaylistmanagerjpa.service;

import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.repository.IMusicRepository;
import com.shadril.musicplaylistmanagerjpa.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private IPlaylistRepository playlistRepository;

    @Autowired
    private IMusicRepository musicRepository;

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Integer id) throws PlaylistNotFoundException {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()){
            return optionalPlaylist.get();
        }
        else{
            throw new PlaylistNotFoundException("Playlist with ID " + id + " not found");
        }
    }

    public void addPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void updatePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void deletePlaylist(Integer playlistId) throws PlaylistNotFoundException{
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);

        if (optionalPlaylist.isPresent()) {
            Playlist playlistToDelete = optionalPlaylist.get();
            playlistToDelete.getMusics().clear();
            playlistRepository.deleteById(playlistId);
        } else {
            throw new PlaylistNotFoundException("Playlist with ID " + playlistId + " not found.");
        }
    }

    public void removeMusicFromPlaylist(Integer playlistId, Integer musicId) throws MusicNotFoundException, PlaylistNotFoundException{

        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Music> optionalMusic = musicRepository.findById(musicId);

        if(optionalPlaylist.isPresent()) {
            if(optionalMusic.isPresent()) {
                Music musicToRemove = optionalMusic.get();
                Playlist playlist = optionalPlaylist.get();
                playlist.getMusics().remove(musicToRemove);
                playlistRepository.save(playlist);
            } else {
                throw new MusicNotFoundException("Music with ID " + musicId + " not found.");
            }
        } else {
            throw new PlaylistNotFoundException("Playlist with ID " + playlistId + " not found.");
        }
    }

    public void addMusicToPlaylist(Integer playlistId, Integer musicId) throws MusicNotFoundException, PlaylistNotFoundException{

        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Music> optionalMusic = musicRepository.findById(musicId);

        if(optionalPlaylist.isPresent()){
            if(optionalMusic.isPresent()) {
                Playlist playlist = optionalPlaylist.get();
                Music music = optionalMusic.get();
                playlist.getMusics().add(music);
                playlistRepository.save(playlist);
            } else {
                throw new MusicNotFoundException("Music with ID " + musicId + " not found.");
            }
        } else {
            throw new PlaylistNotFoundException("Playlist with ID " + playlistId + " not found.");
        }
    }
}
