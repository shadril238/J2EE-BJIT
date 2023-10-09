package com.nuri.socialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDto {

    private Long id;
    private String groupName;
    private String groupDescription;
    private Long userId;
}
