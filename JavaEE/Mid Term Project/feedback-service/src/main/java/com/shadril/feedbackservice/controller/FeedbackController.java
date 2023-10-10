package com.shadril.feedbackservice.controller;

import com.shadril.feedbackservice.dto.FeedbackDto;
import com.shadril.feedbackservice.dto.ProgressTrackDto;
import com.shadril.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackService.submitFeedback(feedbackDto);
        return new ResponseEntity<>("Feedback submitted successfully", HttpStatus.OK);
    }

    @GetMapping("/feedback/all")
    public ResponseEntity<List<FeedbackDto>> getAllFeedback() {
        return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
    }
}
