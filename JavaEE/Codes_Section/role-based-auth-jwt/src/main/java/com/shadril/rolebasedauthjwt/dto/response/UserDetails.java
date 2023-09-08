package com.shadril.rolebasedauthjwt.dto.response;

import com.shadril.rolebasedauthjwt.entity.Role;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetails {
    private String username;
    private String email;
    private boolean isEnabled;
    private boolean isNonLocked;
    private Set<Role> roles;
}
