package com.nuri.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPreferenceDto {

    private Long id;
    private Long userId;
    private String userEmail;
    private boolean diet;
    private boolean exercise;
    private boolean mentalHealth;
    private boolean sleep;
}

