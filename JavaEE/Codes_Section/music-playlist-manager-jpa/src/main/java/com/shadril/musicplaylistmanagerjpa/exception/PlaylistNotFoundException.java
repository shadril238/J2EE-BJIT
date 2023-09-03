package com.shadril.musicplaylistmanagerjpa.exception;

public class PlaylistNotFoundException extends Exception{
    private static final String MESSAGE = "The playlist you requested is not found!";

    public PlaylistNotFoundException() {
        super(MESSAGE);
    }

    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
