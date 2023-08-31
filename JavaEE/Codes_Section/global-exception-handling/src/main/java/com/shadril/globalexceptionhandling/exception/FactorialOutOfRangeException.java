package com.shadril.globalexceptionhandling.exception;

public class FactorialOutOfRangeException extends RuntimeException{
    private static final String MESSAGE = "Factorial input is out of acceptable range.";
    public FactorialOutOfRangeException(){
        super(MESSAGE);
    }
    public FactorialOutOfRangeException(String message){
        super(message);
    }

}
