package com.shadril.feedbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRequestDto {
    private Long feedbackId;
    private Long userId;
    private Long recommendationId;
    private String feedbackText;
    private Double feedbackRating;
    private LocalDateTime timestamp;
}
