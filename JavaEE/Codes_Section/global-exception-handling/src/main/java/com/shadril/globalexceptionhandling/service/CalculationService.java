package com.shadril.globalexceptionhandling.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    public Integer divide(Integer a, Integer b) throws ArithmeticException{
        return a/b;
    }
}
