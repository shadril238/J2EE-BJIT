package com.shadril.feedbackservice.service;

import com.shadril.feedbackservice.dto.FeedbackDto;

public interface FeedbackService {
    void submitFeedback(FeedbackDto feedbackDto);
}
