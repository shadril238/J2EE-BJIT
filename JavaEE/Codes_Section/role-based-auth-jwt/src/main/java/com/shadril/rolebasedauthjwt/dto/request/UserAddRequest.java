
package com.shadril.rolebasedauthjwt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
