package com.example.userservice.controller;


import com.example.userservice.dto.HealthDataDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.Implementation.HealthDataServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthDataController {

    @Autowired
    private HealthDataServiceImplementation healthDataServiceImplementation;

    @PostMapping("/users/healthdata")
    public ResponseEntity<?> createHealthData (@RequestBody HealthDataDto healthDataDto) {
        try {
            healthDataServiceImplementation.createHealthData(healthDataDto);
            return new ResponseEntity<>("Health data added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/healthdata")
    public ResponseEntity<?> getHealthData () {
        try {
             HealthDataDto healthDataDto = healthDataServiceImplementation.getHealthData();
            return new ResponseEntity<>(healthDataDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/healthdata/{userId}")
    public ResponseEntity<?> getAllHealthDataById (@PathVariable Long userId) {
        try {
            List<HealthDataDto> healthDataDto = healthDataServiceImplementation.getAllHealthDataById(userId);
            return new ResponseEntity<>(healthDataDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/healthdata/all")
    public ResponseEntity<?> getAllHealthData () {
        try {
            List <HealthDataDto> allHealthData = healthDataServiceImplementation.getAllHealthData();
            return new ResponseEntity<>(allHealthData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
