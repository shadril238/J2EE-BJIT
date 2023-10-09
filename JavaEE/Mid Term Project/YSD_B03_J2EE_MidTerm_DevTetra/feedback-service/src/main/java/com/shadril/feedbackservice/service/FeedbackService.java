package com.shadril.feedbackservice.service;

import com.shadril.feedbackservice.dto.FeedbackRequestDto;
import com.shadril.feedbackservice.dto.UserFeedbackDto;
import com.shadril.feedbackservice.exception.FeedbackNotFoundException;
import com.shadril.feedbackservice.exception.RecommendationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    void submitFeedback(FeedbackRequestDto feedbackRequestDto) throws RecommendationNotFoundException;
    List<FeedbackRequestDto> getAllFeedback() throws FeedbackNotFoundException;
    Optional<UserFeedbackDto> getAllFeedbackByUser(Long userId) throws FeedbackNotFoundException;

    List<FeedbackRequestDto> getFeedbacks() throws FeedbackNotFoundException;
}
