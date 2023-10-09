package com.shadril.feedbackservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFeedbackDto {
    private Long userId;
    private String email;
    private String name;
    private Integer age;
    private String gender;
    private List<FeedbackResponseDto> feedbacks;
}
