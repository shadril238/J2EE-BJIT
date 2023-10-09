package com.nuri.socialservice.dto;

import com.nuri.socialservice.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndividualPostInfoDto {

    private Long postId;
    private String postDetails;
    private Long countLikes;
    private List<Comment> comments;
}
