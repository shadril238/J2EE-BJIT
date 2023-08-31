package com.shadril.globalexceptionhandling.exception;

public class CustomException extends Exception{
    private static final String MESSAGE = "Custom Exception!";
    public CustomException(){
        super(MESSAGE);
    }
    public CustomException(String message){
        super(message);
    }
}
