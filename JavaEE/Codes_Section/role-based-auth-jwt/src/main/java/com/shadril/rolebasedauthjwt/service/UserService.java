package com.shadril.rolebasedauthjwt.service;


import com.shadril.rolebasedauthjwt.dto.request.RegisterRequest;
import com.shadril.rolebasedauthjwt.dto.request.UserAddRequest;
import com.shadril.rolebasedauthjwt.dto.request.UserUpdateRequest;
import com.shadril.rolebasedauthjwt.dto.response.RegisterResponse;
import com.shadril.rolebasedauthjwt.dto.response.UserAddResponse;
import com.shadril.rolebasedauthjwt.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
    RegisterResponse save(RegisterRequest request);
    UserAddResponse save(UserAddRequest request);

    List<User> getAllUser();
    void deleteUserById(Long id);
    User updateUser(UserUpdateRequest userUpdateRequest);
    void activateDeactivateUserAccount(User user);
    Optional<User> existsByUserId(long id);
    Optional<User> findById(long id);
    User resetPassword(User user);
}
