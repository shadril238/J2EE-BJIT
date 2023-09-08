package com.shadril.rolebasedauthjwt.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserAddResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
