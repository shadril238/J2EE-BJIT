package com.shadril.onlinebooklibraryapplication.exception;

public class BookAlreadyBorrowedException extends RuntimeException{
    public BookAlreadyBorrowedException(String message){
        super(message);
    }
}
