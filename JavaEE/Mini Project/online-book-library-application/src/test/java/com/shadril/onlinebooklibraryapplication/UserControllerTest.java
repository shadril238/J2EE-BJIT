package com.shadril.onlinebooklibraryapplication;

import com.shadril.onlinebooklibraryapplication.controller.UserController;
import com.shadril.onlinebooklibraryapplication.dto.UserDTO;
import com.shadril.onlinebooklibraryapplication.dto.UserLoginRequestModelDTO;
import com.shadril.onlinebooklibraryapplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin() {
        UserLoginRequestModelDTO userLoginRequest = new UserLoginRequestModelDTO();
        userLoginRequest.setEmail("testuser@example.com");
        userLoginRequest.setPassword("password");

        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setEmail("testuser@example.com");
        userDto.setRole("USER");

        Mockito.when(userService.getUser(userLoginRequest.getEmail())).thenReturn(userDto);
        ResponseEntity<?> responseEntity = userController.login(userLoginRequest, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
