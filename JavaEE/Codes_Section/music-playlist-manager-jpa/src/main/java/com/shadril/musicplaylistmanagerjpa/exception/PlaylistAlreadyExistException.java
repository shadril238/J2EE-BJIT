package com.shadril.musicplaylistmanagerjpa.exception;

public class PlaylistAlreadyExistException extends Exception{
    private static final String MESSAGE = "The playlist is already exist!";

    public PlaylistAlreadyExistException() {
        super(MESSAGE);
    }

    public PlaylistAlreadyExistException(String message) {
        super(message);
    }
}
