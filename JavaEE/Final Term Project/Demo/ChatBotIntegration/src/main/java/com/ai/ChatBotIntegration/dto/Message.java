package com.ai.ChatBotIntegration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String role;
    private String content;

    public Message(String user, PatientHealthData prompt) {
        this.role=user;
        this.content= String.valueOf(prompt);
    }
}
