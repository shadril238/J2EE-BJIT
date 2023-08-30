package com.shadril.globalexceptionhandling.controller;

import com.shadril.globalexceptionhandling.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private CalculationService calculationService;

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam Integer a, @RequestParam Integer b) throws ArithmeticException {
        return new ResponseEntity<>(calculationService.divide(a, b), HttpStatus.OK);
    }
}
