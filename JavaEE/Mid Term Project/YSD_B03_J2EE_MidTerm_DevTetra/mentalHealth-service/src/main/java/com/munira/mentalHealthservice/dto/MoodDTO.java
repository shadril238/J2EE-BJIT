package com.munira.mentalHealthservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoodDTO {
    private Long moodId;
    private Long userId;
    private String moodState;
    private String stressType;
    private LocalDateTime timeStamp;
    private String moodNote;
}
