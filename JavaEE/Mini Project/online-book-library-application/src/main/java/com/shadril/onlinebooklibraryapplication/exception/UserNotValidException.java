package com.shadril.onlinebooklibraryapplication.exception;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String message) {
        super(message);
    }
}
