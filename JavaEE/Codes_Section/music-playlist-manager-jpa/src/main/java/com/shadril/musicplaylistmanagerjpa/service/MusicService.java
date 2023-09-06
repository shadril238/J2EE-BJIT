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

    public Boolean musicAlreadyExist(String title) {
        return musicRepository.findMusicByTitle(title).isPresent();
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Integer id)
            throws MusicNotFoundException {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if(optionalMusic.isPresent()){
            return optionalMusic.get();
        }
        else{
            throw new MusicNotFoundException();
        }
    }

    public Music getMusicByTitle(String title)
            throws MusicNotFoundException{
        Optional<Music> optionalMusic = musicRepository.findMusicByTitle(title);
        if(optionalMusic.isPresent()) {
            return optionalMusic.get();
        }
        else {
            throw new MusicNotFoundException();
        }
    }

    public void addMusic(Music music)
            throws MusicAlreadyExistException {
        if (musicRepository.findMusicByTitle(music.getTitle()).isPresent()) {
            throw new MusicAlreadyExistException();
        }
        musicRepository.save(music);
    }

    public void updateMusic(Music music)
            throws MusicNotFoundException, MusicAlreadyExistException{
        if (musicRepository.findMusicByTitle(music.getTitle()).isPresent()) {
            throw new MusicAlreadyExistException();
        }

        Optional<Music> existingMusic = musicRepository.findById(music.getId());
        if (existingMusic.isPresent()) {
            musicRepository.save(music);
        } else {
            throw new MusicNotFoundException();
        }
    }

    public void deleteMusic(Integer id)
            throws MusicNotFoundException{

        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();

            List<Playlist> playlists = playlistRepository.findAll();
            boolean isMusicUsedInPlaylists = playlists.stream()
                    .anyMatch(playlist -> playlist.getMusics().contains(music));

            if (isMusicUsedInPlaylists) {
                playlists.forEach(playlist -> playlist.getMusics().remove(music));
                playlistRepository.saveAll(playlists);
            }
            musicRepository.delete(music);
        } else {
            throw new MusicNotFoundException();
        }
    }

}
