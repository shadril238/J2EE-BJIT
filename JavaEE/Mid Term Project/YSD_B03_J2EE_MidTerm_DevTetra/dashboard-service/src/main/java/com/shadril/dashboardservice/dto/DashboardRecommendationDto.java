package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardRecommendationDto {
    private Long recommendationId;
    private String recommendationType;
    private String recommendationCategory;
    private String recommendationText;
    private String recommendationDecision;
    private Double meanRating;

}
