package com.spring.securityPractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "Hello2";
    }
}
