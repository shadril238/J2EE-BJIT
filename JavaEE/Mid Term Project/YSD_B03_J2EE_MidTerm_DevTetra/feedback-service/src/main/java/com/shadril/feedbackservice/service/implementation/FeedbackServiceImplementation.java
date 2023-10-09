package com.shadril.feedbackservice.service.implementation;

import com.shadril.feedbackservice.dto.*;
import com.shadril.feedbackservice.entity.FeedbackEntity;
import com.shadril.feedbackservice.exception.FeedbackNotFoundException;
import com.shadril.feedbackservice.exception.RecommendationNotFoundException;
import com.shadril.feedbackservice.networkmanager.RecommendationServiceFeignClient;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeedbackServiceImplementation implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    private RecommendationServiceFeignClient recommendationServiceFeignClient;

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
    public void submitFeedback(FeedbackRequestDto feedbackRequestDto)
            throws RecommendationNotFoundException {
        if (feedbackRequestDto.getRecommendationId() == null) {
            throw new RecommendationNotFoundException("Recommendation id can not be null");
        }
        Optional<RecommendationDto> recommendation = Optional.ofNullable(recommendationServiceFeignClient
                .getRecommendationByRecommendationId(feedbackRequestDto.getRecommendationId()));
        if (recommendation.isEmpty()) {
            throw new RecommendationNotFoundException("Recommendation not found by id");
        }

        feedbackRequestDto.setUserId(getCurrentUserId());
        feedbackRequestDto.setTimestamp(LocalDateTime.now());
        FeedbackEntity feedbackEntity = new ModelMapper()
                .map(feedbackRequestDto, FeedbackEntity.class);
        feedbackRepository.save(feedbackEntity);
    }
    // Get all feedbacks of current user from the feedback repository.
    @Override
    public List<FeedbackRequestDto> getAllFeedback()
            throws FeedbackNotFoundException {
        List<FeedbackEntity> feedbackEntityList = feedbackRepository.findAll();

        if (feedbackEntityList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedbacks found");
        }
        return feedbackEntityList
                .stream()
                .filter(feedbackEntity -> feedbackEntity.getUserId().equals(getCurrentUserId()))
                .map(feedbackEntity -> new ModelMapper().map(feedbackEntity, FeedbackRequestDto.class))
                .toList();
    }
    // Get all feedbacks of a user from the feedback repository.
    @Override
    public Optional<UserFeedbackDto> getAllFeedbackByUser(Long userId)
            throws FeedbackNotFoundException {
        UserFeedbackDto userFeedbackDto = new UserFeedbackDto();
        UserDto userDto = getUserById(userId);

        userFeedbackDto.setUserId(userDto.getUserId());
        userFeedbackDto.setEmail(userDto.getEmail());
        userFeedbackDto.setName(userDto.getName());
        userFeedbackDto.setAge(userDto.getAge());
        userFeedbackDto.setGender(userDto.getGender());

        List<FeedbackEntity> feedbackEntityList = feedbackRepository.findAllByUserId(userId);

        if (feedbackEntityList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedbacks found");
        }

        List<FeedbackResponseDto> feedbackResponseList = new ArrayList<>();

        for (FeedbackEntity feedbackEntity : feedbackEntityList) {
            feedbackResponseList.add(convertToFeedbackResponseDto(feedbackEntity));
        }

        userFeedbackDto.setFeedbacks(feedbackResponseList);

        return Optional.of(userFeedbackDto);
    }

    @Override
    public List<FeedbackRequestDto> getFeedbacks() throws FeedbackNotFoundException {
        List<FeedbackEntity> feedbackEntityList = feedbackRepository.findAll();

        if (feedbackEntityList.isEmpty()) {
            throw new FeedbackNotFoundException("No feedbacks found");
        }
        return feedbackEntityList
                .stream()
                .map(feedbackEntity -> new ModelMapper().map(feedbackEntity, FeedbackRequestDto.class))
                .toList();
    }

    // Get the user details by user id from the user service feign client.
    private UserDto getUserById(Long userId) {
        return userServiceFeignClient.userDetailsByUserId(userId);
    }

    // Convert feedback entity to feedback response dto.
    private FeedbackResponseDto convertToFeedbackResponseDto(FeedbackEntity feedbackEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(feedbackEntity, FeedbackResponseDto.class);
    }
}
