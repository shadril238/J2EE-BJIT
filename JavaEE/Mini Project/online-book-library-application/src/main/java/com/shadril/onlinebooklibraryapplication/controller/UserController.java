package com.shadril.onlinebooklibraryapplication.controller;

import com.shadril.onlinebooklibraryapplication.constants.AppConstants;
import com.shadril.onlinebooklibraryapplication.dto.UserDTO;
import com.shadril.onlinebooklibraryapplication.dto.UserLoginRequestModelDTO;
import com.shadril.onlinebooklibraryapplication.entity.Book;
import com.shadril.onlinebooklibraryapplication.entity.BorrowBook;
import com.shadril.onlinebooklibraryapplication.exception.UserNotFoundException;
import com.shadril.onlinebooklibraryapplication.service.BookService;
import com.shadril.onlinebooklibraryapplication.service.UserService;
import com.shadril.onlinebooklibraryapplication.utils.JWTUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BookService bookService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> userDetailsByUserId(@PathVariable Long userId) {
        try {
            UserDTO user = userService.getUserByUserId(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register (@RequestBody UserDTO userDto) {
        try {
            UserDTO createdUser = userService.createUser(userDto);
            String accessToken = JWTUtils.generateToken(createdUser.getEmail());
            Map<String, Object> registerResponse = new HashMap<>();
            registerResponse.put("user", createdUser);
            registerResponse.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestModelDTO userLoginReqModel, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginReqModel.getEmail(), userLoginReqModel.getPassword()));
            UserDTO userDto = userService.getUser(userLoginReqModel.getEmail());
            String accessToken = JWTUtils.generateToken(userDto.getEmail());
            Map<String, Object> loginResponse = new HashMap<>();
            loginResponse.put("userId", userDto.getId());
            loginResponse.put("email", userDto.getEmail());
            loginResponse.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Wrong Email or other exception!", HttpStatus.UNAUTHORIZED);

        }
    }

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<List<Book>> retrievedBooks(@PathVariable Long userId)
            throws UserNotFoundException {
        List<Book> bookList = bookService.retrievedBooks(userId);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/borrowed-books")
    public ResponseEntity<List<Book>> retrievedBorrowedBooks(@PathVariable Long userId)
            throws UserNotFoundException {
        List<Book> bookList = bookService.retrievedBorrowedBooks(userId);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/history")
    public ResponseEntity<List<BorrowBook>> borrowingHistory(@PathVariable Long userId)
            throws UserNotFoundException{
        return new ResponseEntity<>(bookService.borrowHistory(userId), HttpStatus.OK);
    }
}
