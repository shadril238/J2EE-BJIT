package com.shadril.feedbackservice.service.implementation;

import ch.qos.logback.core.LayoutBase;
import com.shadril.feedbackservice.dto.FeedbackDto;
import com.shadril.feedbackservice.dto.UserDto;
import com.shadril.feedbackservice.entity.FeedbackEntity;
import com.shadril.feedbackservice.networkmanager.UserServiceFeignClient;
import com.shadril.feedbackservice.repository.FeedbackRepository;
import com.shadril.feedbackservice.service.FeedbackService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FeedbackServiceImplementation implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    // Get the current user from the JWT token and feign client user service.
    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userServiceFeignClient.userDetailsByEmail(userMail);
    }
    // Get the current user id from the JWT token and feign client user service.
    private Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }
    // Save the feedback to the feedback repository.
    @Override
    public void submitFeedback(FeedbackDto feedbackDto) {
        feedbackDto.setUserId(getCurrentUserId());
        feedbackDto.setTimestamp(LocalDateTime.now());
        FeedbackEntity feedbackEntity = new ModelMapper().map(feedbackDto, FeedbackEntity.class);
        feedbackRepository.save(feedbackEntity);
    }
    // Get all feedbacks of current user from the feedback repository.
    @Override
    public List<FeedbackDto> getAllFeedback() {
        List<FeedbackEntity> feedbackEntityList = feedbackRepository.findAll();
        return feedbackEntityList
                .stream()
                .filter(feedbackEntity -> feedbackEntity.getUserId().equals(getCurrentUserId()))
                .map(feedbackEntity -> new ModelMapper().map(feedbackEntity, FeedbackDto.class))
                .toList();
    }
}
