package com.example.recommendationservice.service;

import com.example.recommendationservice.dto.RecommendationDto;
import com.example.recommendationservice.entity.RecommendationEntity;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {
    void createRecommendations(List<RecommendationDto> recommendationDto);
    List<RecommendationDto> getDietRecommendations();
    List<RecommendationDto> getExerciseRecommendations();

    public Optional <RecommendationDto> getRecommendationByRecommendationId (Long recommendationId) throws Exception;

    List<RecommendationDto> getSleepRecommendations();

    List<RecommendationDto> getMentalHealthRecommendations();
}
