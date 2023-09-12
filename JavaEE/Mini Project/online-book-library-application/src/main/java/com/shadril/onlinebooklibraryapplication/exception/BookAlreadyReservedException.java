package com.shadril.onlinebooklibraryapplication.exception;

public class BookAlreadyReservedException extends RuntimeException{
    public BookAlreadyReservedException(String message){
        super(message);
    }
}
