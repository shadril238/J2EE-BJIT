package com.rezaur.main.controller;

import com.rezaur.main.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @GetMapping("/subtract")
    public Object subtract(@RequestParam Integer a, @RequestParam Integer b) throws Exception {
        return calculationService.subtract(a, b);
    }
}
