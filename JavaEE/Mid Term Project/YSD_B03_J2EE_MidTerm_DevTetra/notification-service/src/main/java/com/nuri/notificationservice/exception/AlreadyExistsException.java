package com.nuri.notificationservice.exception;

public class AlreadyExistsException extends Exception{

    public AlreadyExistsException(String MESSAGE) {
        super(MESSAGE);
    }
}