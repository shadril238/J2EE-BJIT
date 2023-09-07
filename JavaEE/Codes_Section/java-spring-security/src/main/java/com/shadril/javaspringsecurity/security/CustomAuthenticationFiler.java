package com.shadril.javaspringsecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadril.javaspringsecurity.SpringApplicationContext;
import com.shadril.javaspringsecurity.constants.AppConstants;
import com.shadril.javaspringsecurity.model.UserDTO;
import com.shadril.javaspringsecurity.model.UserLoginRequestModel;
import com.shadril.javaspringsecurity.service.UserService;
import com.shadril.javaspringsecurity.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFiler extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {

        try {
            UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword())
            );
        } catch (IOException e) {
            log.info("Exception occurred at attemptAuthentication method: {}",e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//        throws IOException, ServletException {
//        String user  = ((User)authResult.getPrincipal()).getUsername();
//        String accessToken = JWTUtils.generateToken(user);
//        UserService userService = (UserService) SpringApplicationContext.getBean("userService");
//        UserDTO userDTO = userService.getUser(user);
//        response.addHeader("userId", userDTO.getUserId());
//        response.addHeader(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
//    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String user = ((User) authResult.getPrincipal()).getUsername();
        String accessToken = JWTUtils.generateToken(user);
        UserService userService = (UserService) SpringApplicationContext.getBean("userService");
        UserDTO userDTO = userService.getUser(user);

        response.setContentType("application/json");

        response.getWriter().write("{\"accessToken\":\"" + AppConstants.TOKEN_PREFIX + accessToken + "\",\"userId\":\"" + userDTO.getUserId() + "\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String errorMessage = "Authentication failed: " + failed.getMessage();
        response.getWriter().write(errorMessage);
    }


}
