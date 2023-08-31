package com.shadril.globalexceptionhandling.exception;

public class SquareRootNegativeInputException extends RuntimeException{
    private static final String MESSAGE = "Cannot calculate square root of a negative number.";
    public SquareRootNegativeInputException(){
        super(MESSAGE);
    }
    public SquareRootNegativeInputException(String message){
        super(message);
    }
}
