package com.nuri.socialservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private Long postId;
    private String commentDetail;
    private Long userIdGaveComment;

    @Temporal(TemporalType.DATE)
    private Date dateTime;
}
