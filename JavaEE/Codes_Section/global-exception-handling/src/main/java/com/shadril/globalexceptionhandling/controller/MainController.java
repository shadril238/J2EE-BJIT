package com.shadril.globalexceptionhandling.controller;

import com.shadril.globalexceptionhandling.exception.FactorialOutOfRangeException;
import com.shadril.globalexceptionhandling.exception.InfinityException;
import com.shadril.globalexceptionhandling.exception.SquareRootNegativeInputException;
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
    public ResponseEntity<?> divide(@RequestParam Integer a, @RequestParam Integer b) throws InfinityException {
        return new ResponseEntity<>(calculationService.divide(a, b), HttpStatus.OK);
    }

    @GetMapping("/factorial")
    public ResponseEntity<?> factorial(@RequestParam Integer n) throws FactorialOutOfRangeException {
        return new ResponseEntity<>(calculationService.factorial(n), HttpStatus.OK);
    }

    @GetMapping("/square-root")
    public ResponseEntity<?> squareRoot(@RequestParam Double n) throws SquareRootNegativeInputException {
        return new ResponseEntity<>(calculationService.squareRoot(n), HttpStatus.OK);
    }
}
