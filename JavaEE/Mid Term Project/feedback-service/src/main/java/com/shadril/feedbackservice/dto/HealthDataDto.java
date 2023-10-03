package com.shadril.feedbackservice.dto;

import java.time.LocalDateTime;

public class HealthDataDto {
    private Long healthDataId;
    private Long userId;
    private Double height;
    private Double weight;
    private Double sleepTime;
    private String bloodPressure;
    private LocalDateTime timestamp;
}
