package com.shadril.feedbackservice.controller;

import com.shadril.feedbackservice.exception.FeedbackNotFoundException;
import com.shadril.feedbackservice.exception.ProgressInsightsNotFoundException;
import com.shadril.feedbackservice.exception.ProgressTrackNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeedbackNotFoundException.class)
    public String handleResourceNotFoundException(String message) {
        return message;
    }

    @ExceptionHandler(ProgressTrackNotFoundException.class)
    public String handleProgressTrackNotFoundException(String message) {
        return message;
    }

    @ExceptionHandler(ProgressInsightsNotFoundException.class)
    public String handleProgressInsightsNotFoundException(String message) {
        return message;
    }
}
