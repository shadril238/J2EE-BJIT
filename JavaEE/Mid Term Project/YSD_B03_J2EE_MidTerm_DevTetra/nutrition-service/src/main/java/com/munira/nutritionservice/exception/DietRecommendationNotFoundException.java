package com.munira.nutritionservice.exception;

public class DietRecommendationNotFoundException extends RuntimeException{
    public DietRecommendationNotFoundException(String message) {
        super(message);
    }
}
