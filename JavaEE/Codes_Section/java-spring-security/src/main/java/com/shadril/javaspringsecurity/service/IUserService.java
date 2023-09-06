package com.shadril.javaspringsecurity.service;

import com.shadril.javaspringsecurity.model.UserDTO;

public interface IUserService {
    UserDTO createUser(UserDTO user) throws Exception;
    UserDTO getUser(String email);
    UserDTO getUserByUserId(String id) throws Exception;

}
