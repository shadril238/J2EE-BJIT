package com.shadril.dashboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackResponseDto {
    private Long feedbackId;
    private Long recommendationId;
    private String feedbackText;
    private Double feedbackRating;
    private String satisfactionLevel;
    private LocalDateTime timestamp;
}