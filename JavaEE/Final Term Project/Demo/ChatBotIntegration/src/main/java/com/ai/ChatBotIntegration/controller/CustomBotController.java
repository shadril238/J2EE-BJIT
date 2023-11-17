package com.ai.ChatBotIntegration.controller;


import com.ai.ChatBotIntegration.entity.RecommendationEntity;
import com.ai.ChatBotIntegration.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomBotController {

    private final ChatGPTService chatGPTService;
    @PostMapping("/v1/ai/chat")
    public ResponseEntity<String> chat(@RequestBody String message){
        return ResponseEntity.ok(chatGPTService.chat(message));
    }

    @GetMapping("/getRecommendation")
    public ResponseEntity<RecommendationEntity> getRecommendationByMail() {
        RecommendationEntity recommendation = chatGPTService.getRecommendationByMail();
        return ResponseEntity.ok(recommendation) ;
    }
}
