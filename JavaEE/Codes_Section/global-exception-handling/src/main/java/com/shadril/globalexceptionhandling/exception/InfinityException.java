package com.shadril.globalexceptionhandling.exception;

public class InfinityException extends RuntimeException {
    private static final String MESSAGE = "Infinity value encountered, which cannot be handled by the application.";
    public InfinityException() {
        super(MESSAGE);
    }
    public InfinityException(String message) {
        super(message);
    }
}
