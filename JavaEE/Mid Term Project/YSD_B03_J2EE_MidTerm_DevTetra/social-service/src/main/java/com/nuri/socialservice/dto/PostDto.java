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
public class PostDto {

    private Long id;
    private Long userId;
    private Long groupId;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateTime;
}
