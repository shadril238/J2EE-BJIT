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
public class TrackingDataDto {
    private List<HealthDataDto> healthData;
    private List<MentalHealthDataDto> mentalHealthData;
}
