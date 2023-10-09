package com.shadril.feedbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentalHealthDataDto {
    private Long moodId;
    private String moodState;
    private String stressType;
    private LocalDateTime timeStamp;
}
