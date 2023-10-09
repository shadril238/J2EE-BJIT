package com.shadril.feedbackservice.controller;

import com.shadril.feedbackservice.dto.FeedbackRequestDto;
import com.shadril.feedbackservice.dto.UserFeedbackDto;
import com.shadril.feedbackservice.exception.FeedbackNotFoundException;
import com.shadril.feedbackservice.exception.RecommendationNotFoundException;
import com.shadril.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto){
        try{
            feedbackService.submitFeedback(feedbackRequestDto);
            return new ResponseEntity<>("Your feedback submitted successfully", HttpStatus.CREATED);
        } catch (RecommendationNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Recommendation not found by id", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackRequestDto>> getAllFeedback() {
        try{
            return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
        } catch (FeedbackNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserFeedbackDto> getAllFeedbackByUserId(@PathVariable Long userId)
            throws FeedbackNotFoundException{
        return new ResponseEntity<>(feedbackService.getAllFeedbackByUser(userId).get(), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<FeedbackRequestDto>> getFeedbacks() {
        try{
            return new ResponseEntity<>(feedbackService.getFeedbacks(), HttpStatus.OK);
        } catch (FeedbackNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
