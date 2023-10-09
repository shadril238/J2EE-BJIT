package com.munira.mentalHealthservice.service;

import com.munira.mentalHealthservice.dto.UserDTO;

public interface UserService {
    UserDTO getCurrentUser() throws Exception;
}
