package com.nuri.socialservice.dto;

import com.nuri.socialservice.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupPostDto {

    private List<IndividualPostInfoDto> postInfoDto;
}
