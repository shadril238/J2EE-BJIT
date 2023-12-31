package com.shadril.musicplaylistmanagerjpa.exception;

public class MusicAlreadyExistException extends Exception{
    private static final String MESSAGE = "The music is already exist!";

    public MusicAlreadyExistException() {
        super(MESSAGE);
    }

    public MusicAlreadyExistException(String message) {
        super(message);
    }
}
