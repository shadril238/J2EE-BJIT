package com.munira.nutritionservice.exception;

public class HealthDataNotFoundException extends RuntimeException{
    public HealthDataNotFoundException(String message) {
        super(message);
    }
}
