package com.ai.ChatBotIntegration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDto {
    private Long recommendationId;
    private String mail;
    private String diagnosis;
    private String treatmentPlan;
    private String medicationRecommendation;
}
