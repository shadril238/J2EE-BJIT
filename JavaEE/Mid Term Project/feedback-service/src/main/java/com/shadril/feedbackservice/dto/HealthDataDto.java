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
public class HealthDataDto {
    private Long healthDataId;
    private Long userId;
    private Double height;
    private Double weight;
    private Double sleepTime;
    private String bloodPressure;
    private LocalDateTime timestamp;
    private Double bmi;
    private HealthStatusDto healthStatus;
}
