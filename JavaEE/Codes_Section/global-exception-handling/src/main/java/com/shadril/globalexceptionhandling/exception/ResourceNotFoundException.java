package com.shadril.globalexceptionhandling.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Requested resource not found exception!";
    public ResourceNotFoundException(){
        super(MESSAGE);
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
