package com.shadril.dashboardservice.controller;

import com.shadril.dashboardservice.exception.FeedbackNotFoundException;
import com.shadril.dashboardservice.exception.ProgressNotFoundException;
import com.shadril.dashboardservice.exception.RecommendationNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeedbackNotFoundException.class)
    public String handleFeedbackNotFoundException(String message) {
        return message;
    }

    @ExceptionHandler(ProgressNotFoundException.class)
    public String handleProgressNotFoundException(String message) {
        return message;
    }

    @ExceptionHandler(RecommendationNotFoundException.class)
    public String handleRecommendationNotFoundException(String message) {
        return message;
    }
}
