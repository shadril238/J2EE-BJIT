package com.example.recommendationservice.repository;

import com.example.recommendationservice.entity.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {
    List<RecommendationEntity> findAllByRecommendationTypeAndRecommendationCategory(String recommendationCategory, String recommendationType);
}
