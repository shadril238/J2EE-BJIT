package com.shadril.musicplaylistmanagerjpa.service;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.model.Music;
import com.shadril.musicplaylistmanagerjpa.model.Playlist;
import com.shadril.musicplaylistmanagerjpa.repository.IMusicRepository;
import com.shadril.musicplaylistmanagerjpa.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    @Autowired
    private IMusicRepository musicRepository;

    @Autowired
    private IPlaylistRepository playlistRepository;

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Integer id) throws MusicNotFoundException {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if(optionalMusic.isPresent()){
            return optionalMusic.get();
        }
        else{
            throw new MusicNotFoundException("Music with ID " + id + " not found");
        }
    }

    public void addMusic(Music music) throws MusicAlreadyExistException {
        String musicTitle = music.getTitle();
        if (musicRepository.musicAlreadyExistByTitle(musicTitle)) {
            throw new MusicAlreadyExistException("Music with title '" + musicTitle + "' already exists.");
        }

        musicRepository.save(music);
    }

    public void updateMusic(Music music) throws MusicNotFoundException{
        Optional<Music> existingMusic = musicRepository.findById(music.getId());
        if (existingMusic.isPresent()) {
            musicRepository.save(music);
        } else {
            throw new MusicNotFoundException("Music with ID " + music.getId() + " not found.");
        }
    }

    public void deleteMusic(Integer id) throws MusicNotFoundException{
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (optionalMusic.isPresent()) {
            Music musicToDelete = optionalMusic.get();

            List<Playlist> playlists = musicToDelete.getPlaylists();
            for (Playlist playlist : playlists) {
                playlist.getMusics().remove(musicToDelete);
            }
            playlistRepository.saveAll(playlists);

            musicRepository.deleteById(id);
        } else {
            throw new MusicNotFoundException("Music with ID " + id + " not found.");
        }
    }
}
