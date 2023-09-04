package com.shadril.basicspringsecuritypractice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Home", HttpStatus.OK);
    }

    @GetMapping("/payment")
    public ResponseEntity<String> payment() {
        return new ResponseEntity<>("Payment", HttpStatus.OK);
    }
}
