package com.shadril.onlinebooklibraryapplication.controller;

import com.shadril.onlinebooklibraryapplication.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookAlreadyExistsException.class})
    public ResponseEntity<String> bookAlreadyExistsException(BookAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<String> bookNotFoundException(BookNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ReviewAlreadyExistsException.class})
    public ResponseEntity<?> reviewAlreadyExistsException(ReviewAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ReviewNotFoundException.class})
    public ResponseEntity<?> reviewNotFoundException(ReviewNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotValidException.class})
    public ResponseEntity<?> userNotValidException(UserNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookAlreadyReservedException.class})
    public ResponseEntity<?> bookAlreadyReservedException(BookAlreadyReservedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookAlreadyBorrowedException.class})
    public ResponseEntity<?> bookAlreadyBorrowedException(BookAlreadyBorrowedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({BookReserveNotFoundException.class})
    public ResponseEntity<?> bookReserveNotFoundException(BookReserveNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
