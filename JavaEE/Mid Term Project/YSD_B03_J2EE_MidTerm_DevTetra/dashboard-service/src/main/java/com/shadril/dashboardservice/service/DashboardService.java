package com.shadril.dashboardservice.service;

import com.shadril.dashboardservice.dto.DashboardRecommendationDto;
import com.shadril.dashboardservice.dto.UserProgressTrackDto;
import com.shadril.dashboardservice.dto.UserRecommendationDto;
import com.shadril.dashboardservice.exception.FeedbackNotFoundException;
import com.shadril.dashboardservice.exception.ProgressNotFoundException;
import com.shadril.dashboardservice.exception.RecommendationNotFoundException;

import java.util.List;

public interface DashboardService {
    UserRecommendationDto recommendationFeedback(Long userId) throws RecommendationNotFoundException, FeedbackNotFoundException;
    UserProgressTrackDto trackUserProgress(Long userId) throws ProgressNotFoundException;
    List<DashboardRecommendationDto> recommendationDecision() throws RecommendationNotFoundException;
}
