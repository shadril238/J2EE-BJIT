package com.shadril.musicplaylistmanagerjpa.service;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.repository.IMusicRepository;
import com.shadril.musicplaylistmanagerjpa.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private IPlaylistRepository playlistRepository;

    @Autowired
    private IMusicRepository musicRepository;

    public Boolean playlistAlreadyExist(String name) {
        return playlistRepository.findPlaylistByName(name).isPresent();
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Integer id)
            throws PlaylistNotFoundException {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        if(optionalPlaylist.isPresent()){
            return optionalPlaylist.get();
        }
        else{
            throw new PlaylistNotFoundException();
        }
    }

    public Playlist getPlaylistByName(String name)
            throws PlaylistNotFoundException {
        Optional<Playlist> optionalPlaylist = playlistRepository.findPlaylistByName(name);
        if(optionalPlaylist.isPresent()) {
            return optionalPlaylist.get();
        }
        else {
            throw new PlaylistNotFoundException();
        }
    }

    public void addPlaylist(Playlist playlist)
            throws PlaylistAlreadyExistException, MusicNotFoundException {
        if(playlistRepository.findPlaylistByName(playlist.getName()).isPresent()){
            throw new PlaylistAlreadyExistException();
        }

        for(Music music : playlist.getMusics()){
            Optional<Music> optionalMusic = musicRepository.findById(music.getId());
            if(optionalMusic.isEmpty()){
                throw new MusicNotFoundException();
            }
        }
        playlistRepository.save(playlist);
    }

    public void updatePlaylist(Playlist playlist)
            throws PlaylistNotFoundException, PlaylistAlreadyExistException{
        if(playlistRepository.findPlaylistByName(playlist.getName()).isPresent()){
            throw new PlaylistAlreadyExistException();
        }
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlist.getId());

        if (optionalPlaylist.isPresent()) {
            List<Music> musicList = optionalPlaylist.get().getMusics();
            playlist.getMusics().clear();
            playlist.setMusics(musicList);
            playlistRepository.save(playlist);
        } else {
            throw new PlaylistNotFoundException();
        }
    }

    public void deletePlaylist(Integer playlistId)
            throws PlaylistNotFoundException{
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);

        if (optionalPlaylist.isPresent()) {
            playlistRepository.delete(optionalPlaylist.get());
        } else {
            throw new PlaylistNotFoundException();
        }
    }

    public void addMusicToPlaylist(Integer playlistId, Integer musicId)
            throws MusicAlreadyExistException, PlaylistNotFoundException, MusicNotFoundException{
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if(optionalPlaylist.isEmpty()){
            throw new PlaylistNotFoundException();
        }
        Playlist playlist = optionalPlaylist.get();

        Optional<Music> optionalMusic = musicRepository.findById(musicId);
        if (optionalMusic.isEmpty()) {
            throw new MusicNotFoundException();
        }
        Music music = optionalMusic.get();

        if(playlist.getMusics().contains(music)){
            throw new MusicAlreadyExistException();
        }

        playlist.getMusics().add(music);
        playlistRepository.save(playlist);
    }

    public void removeMusicFromPlaylist(Integer playlistId, Integer musicId)
            throws PlaylistNotFoundException, MusicNotFoundException{

        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if(optionalPlaylist.isEmpty()){
            throw new PlaylistNotFoundException();
        }
        Playlist playlist = optionalPlaylist.get();

        Optional<Music> optionalMusic = musicRepository.findById(musicId);
        if (optionalMusic.isEmpty()) {
            throw new MusicNotFoundException();
        }
        Music music = optionalMusic.get();
        playlist.getMusics().remove(music);
        playlistRepository.save(playlist);
    }
}
