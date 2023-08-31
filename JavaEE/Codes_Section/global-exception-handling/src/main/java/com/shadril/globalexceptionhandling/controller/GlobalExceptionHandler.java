package com.shadril.globalexceptionhandling.controller;

import com.shadril.globalexceptionhandling.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler({ArithmeticException.class})
//    public ResponseEntity<?> handleArithematicException(){
//        return new ResponseEntity<>(new CustomException().getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler({NullPointerException.class})
//    public ResponseEntity<?> handleNullPointerException(){
//        return new ResponseEntity<>(new CustomException().getMessage(), HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler({SquareRootNegativeInputException.class})
    public ResponseEntity<?> handleSquareRootNegativeInputException(){
        return new ResponseEntity<>(new SquareRootNegativeInputException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FactorialOutOfRangeException.class})
    public ResponseEntity<?> handleFactorialOutOfRangeException(){
        return new ResponseEntity<>(new FactorialOutOfRangeException().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(){
        return new ResponseEntity<>(new ResourceNotFoundException(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InfinityException.class})
    public ResponseEntity<?> handleInfinityException(){
        return new ResponseEntity<>(new InfinityException().getMessage(), HttpStatus.BAD_REQUEST);
    }
}
