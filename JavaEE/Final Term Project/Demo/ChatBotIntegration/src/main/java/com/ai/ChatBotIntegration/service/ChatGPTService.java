package com.ai.ChatBotIntegration.service;

import com.ai.ChatBotIntegration.config.OpenAIConstants;
import com.ai.ChatBotIntegration.dto.ChatGPTRequest;
import com.ai.ChatBotIntegration.dto.ChatGptResponse;
import com.ai.ChatBotIntegration.entity.RecommendationEntity;
import com.ai.ChatBotIntegration.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.mail;

@Service
@RequiredArgsConstructor
public class ChatGPTService {
    private final RestTemplate template;
    @Autowired
    private RecommendationRepository recommendationRepository;
    public String chat(String prompt){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        ChatGPTRequest request = new ChatGPTRequest(OpenAIConstants.GPT_MODEL, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(OpenAIConstants.API_URL, request, ChatGptResponse.class);
        assert chatGptResponse != null;
        String response = chatGptResponse.getChoices().get(0).getMessage().getContent();


        String[] recommendations = response.split("\\.");
        RecommendationEntity recommendationEntity = new RecommendationEntity();
        recommendationEntity.setMail(mail);
        if (recommendations.length > 0) {
            recommendationEntity.setDiagnosis(recommendations[0].trim());
        }
        if (recommendations.length > 1) {
            recommendationEntity.setTreatmentPlan(recommendations[1].trim());
        }
        if (recommendations.length > 2) {
            recommendationEntity.setMedicationRecommendation(recommendations[2].trim());
        }

        Optional<RecommendationEntity> existingRecommendation = recommendationRepository.findByMail(mail);

        if (existingRecommendation.isPresent()) {
            RecommendationEntity existingEntity = existingRecommendation.get();
            existingEntity.setDiagnosis(recommendationEntity.getDiagnosis());
            existingEntity.setTreatmentPlan(recommendationEntity.getTreatmentPlan());
            existingEntity.setMedicationRecommendation(recommendationEntity.getMedicationRecommendation());
            recommendationRepository.save(existingEntity);
        } else {
            recommendationRepository.save(recommendationEntity);
        }

        return response;
    }

    public RecommendationEntity getRecommendationByMail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        return recommendationRepository.findByMail(mail).orElse(null);
    }

}
