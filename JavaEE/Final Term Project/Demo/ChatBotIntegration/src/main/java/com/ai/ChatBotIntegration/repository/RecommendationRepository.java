package com.ai.ChatBotIntegration.repository;

import com.ai.ChatBotIntegration.entity.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {
    Optional<RecommendationEntity> findByMail(String mail);
}
