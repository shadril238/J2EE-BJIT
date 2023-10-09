package com.example.recommendationservice.exceptions;

public class RecommendationIdNotFoundException extends RuntimeException{
    public RecommendationIdNotFoundException(String message) {
        super(message);
    }
}
