package com.shadril.feedbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressInsightsDto {
    private Long userId;
    private List<InsightsDto> insights;
    private ProgressDto progress;
}
