package com.shadril.musicplaylistmanagerjpa.exception;

public class MusicNotFoundException extends Exception{
    private static final String MESSAGE = "The music you requested is not found!";

    public MusicNotFoundException() {
        super(MESSAGE);
    }

    public MusicNotFoundException(String message) {
        super(message);
    }
}
