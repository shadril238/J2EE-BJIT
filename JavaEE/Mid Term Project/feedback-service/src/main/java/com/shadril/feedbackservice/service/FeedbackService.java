package com.shadril.feedbackservice.service;

import com.shadril.feedbackservice.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    void submitFeedback(FeedbackDto feedbackDto);
    List<FeedbackDto> getAllFeedback();
}
