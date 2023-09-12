package com.shadril.onlinebooklibraryapplication.controller;

import com.shadril.onlinebooklibraryapplication.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<String> emailAlreadyExistsException(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> userNotFoundException(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookAlreadyExistsException.class})
    public ResponseEntity<String> bookAlreadyExistsException(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<String> bookNotFoundException(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ReviewAlreadyExistsException.class})
    public ResponseEntity<?> reviewAlreadyExistsException(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ReviewNotFoundException.class})
    public ResponseEntity<?> reviewNotFoundException(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotValidException.class})
    public ResponseEntity<?> userNotValidException(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookAlreadyReservedException.class})
    public ResponseEntity<?> bookAlreadyReservedException(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookAlreadyBorrowedException.class})
    public ResponseEntity<?> bookAlreadyBorrowedException(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookReserveNotFoundException.class})
    public ResponseEntity<?> bookReserveNotFoundException(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
