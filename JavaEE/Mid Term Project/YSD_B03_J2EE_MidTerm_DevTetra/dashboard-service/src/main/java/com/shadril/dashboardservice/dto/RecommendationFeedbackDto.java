package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationFeedbackDto {
    private Long recommendationId;
    private String recommendationType;
    private String recommendationCategory;
    private String recommendationText;
    private List<FeedbackResponseDto> feedbacks;
}
