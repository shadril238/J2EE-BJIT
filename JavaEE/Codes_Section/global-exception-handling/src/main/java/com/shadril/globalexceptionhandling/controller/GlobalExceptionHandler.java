package com.shadril.globalexceptionhandling.controller;

import com.shadril.globalexceptionhandling.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<?> handleArithematicException(){
        return new ResponseEntity<>(new CustomException("Arithematic Exception!").getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<?> handleNullPointerException(){
        return new ResponseEntity<>(new CustomException("Null Pointer Exception!").getMessage(), HttpStatus.BAD_REQUEST);
    }
}
