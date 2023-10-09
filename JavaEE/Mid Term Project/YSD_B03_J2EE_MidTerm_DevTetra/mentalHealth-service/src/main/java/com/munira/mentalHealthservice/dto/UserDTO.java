package com.munira.mentalHealthservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String email;
    private String password;

    private String name;
    private Integer age;
    private String gender;

    private String role;
}
