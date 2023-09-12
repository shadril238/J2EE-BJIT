package com.shadril.onlinebooklibraryapplication.exception;

public class BookReserveNotFoundException extends RuntimeException{
    public BookReserveNotFoundException(String message){
        super(message);
    }
}
