package com.shadril.onlinebooklibraryapplication.service;

import com.shadril.onlinebooklibraryapplication.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDto) throws Exception;
    UserDTO getUser(String email);
    UserDTO getUserByUserId(Long id) throws Exception;
}
