package com.shadril.onlinebooklibraryapplication.service;

import com.shadril.onlinebooklibraryapplication.dto.UserDTO;
import com.shadril.onlinebooklibraryapplication.entity.User;

public interface UserService {
    UserDTO createUser(UserDTO userDto) throws Exception;
    UserDTO getUser(String email);
    UserDTO getUserByUserId(Long id) throws Exception;
    UserDTO getCurrUser();
}
