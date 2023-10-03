package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDto createUser(UserDto userDto) throws Exception;
    UserDto getUserByUserId(Long id) throws Exception;
    UserDto getUser(String email) throws Exception;
    UserDetails loadUserByUsername(String email) throws Exception;
}
