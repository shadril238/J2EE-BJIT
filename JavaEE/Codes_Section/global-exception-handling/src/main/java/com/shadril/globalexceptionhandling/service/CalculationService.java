package com.shadril.globalexceptionhandling.service;

import com.shadril.globalexceptionhandling.exception.FactorialOutOfRangeException;
import com.shadril.globalexceptionhandling.exception.InfinityException;
import com.shadril.globalexceptionhandling.exception.SquareRootNegativeInputException;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    public Integer divide(Integer a, Integer b) throws InfinityException {
        if (b == 0) {
            throw new InfinityException();
        }
        return a/b;
    }

    public Integer factorial(Integer n) throws FactorialOutOfRangeException {
        if (n < 0 || n >= 20) {
            throw new FactorialOutOfRangeException();
        }

        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public Double squareRoot(Double n) throws SquareRootNegativeInputException {
        if (n < 0) {
            throw new SquareRootNegativeInputException();
        }
        return Math.sqrt(n);
    }

}
