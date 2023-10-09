package com.nuri.socialservice.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {

    private Long id;
    private Long postId;
    private Long userId;

    @Temporal(TemporalType.DATE)
    private Date dateTime;
}
