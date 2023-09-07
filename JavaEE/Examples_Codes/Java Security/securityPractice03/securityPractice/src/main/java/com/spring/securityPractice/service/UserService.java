package com.spring.securityPractice.service;


import com.spring.securityPractice.model.UserDto;

public interface UserService {
    UserDto createUser(UserDto user) throws Exception;
    UserDto getUser(String email);

    UserDto getUserByUserId(String id) throws Exception;

}