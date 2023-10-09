package com.nuri.notificationservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String email;
    private String password;

    private String name;
    private Integer age;
    private String gender;

    private String role;
}
