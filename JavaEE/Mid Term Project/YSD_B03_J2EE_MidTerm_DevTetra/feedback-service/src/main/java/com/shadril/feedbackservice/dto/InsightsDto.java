package com.shadril.feedbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsightsDto {
    private String insightCategory;
    private LocalDateTime timestamp;
    private Double percentage;
    private String insightMessage;
}
