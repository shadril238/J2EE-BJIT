package com.example.recommendationservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoodDto {
    private Long moodId;
    private Long userId;
    private String moodState;
    private String stressType;
    private LocalDateTime timeStamp;
    private String moodNote;
}
