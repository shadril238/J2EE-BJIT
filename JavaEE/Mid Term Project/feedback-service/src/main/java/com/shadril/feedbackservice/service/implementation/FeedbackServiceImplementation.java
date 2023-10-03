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

@Service
@Transactional
public class FeedbackServiceImplementation implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public void submitFeedback(FeedbackDto feedbackDto) {
        UserDto currentUser = getCurrentUser();
        feedbackDto.setUserId(currentUser.getUserId());
        feedbackDto.setTimestamp(LocalDateTime.now());
        FeedbackEntity feedbackEntity = new ModelMapper().map(feedbackDto, FeedbackEntity.class);
        feedbackRepository.save(feedbackEntity);
    }
    // Get the current user from the JWT token and feign client user service.
    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userServiceFeignClient.userDetailsByEmail(userMail);
    }
}
