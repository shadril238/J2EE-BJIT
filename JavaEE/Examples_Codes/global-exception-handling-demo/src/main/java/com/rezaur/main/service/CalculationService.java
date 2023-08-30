package com.rezaur.main.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public Integer divide(Integer a, Integer b) throws ArithmeticException {
        return a / b;
    }

    public Integer subtract(Integer a, Integer b) throws Exception {
        int result = a - b;
        if (result <= 0) {
            throw new Exception("Insufficient balance");
        }
        return result;
    }
}
