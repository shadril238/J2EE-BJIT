package com.shadril.musicplaylistmanagerjpa.controller;

import com.shadril.musicplaylistmanagerjpa.exception.MusicAlreadyExistException;
import com.shadril.musicplaylistmanagerjpa.exception.MusicNotFoundException;
import com.shadril.musicplaylistmanagerjpa.exception.PlaylistNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MusicNotFoundException.class})
    public ResponseEntity<?> musicNotFoundException(){
        return new ResponseEntity<>(new MusicNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MusicAlreadyExistException.class})
    public ResponseEntity<?> musicAlreadyExistException() {
        return new ResponseEntity<>(new MusicAlreadyExistException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PlaylistNotFoundException.class})
    public ResponseEntity<?> PlaylistNotFoundException(){
        return new ResponseEntity<>(new PlaylistNotFoundException().getMessage(), HttpStatus.BAD_REQUEST);
    }

}
