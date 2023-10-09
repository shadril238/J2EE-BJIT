package com.nuri.notificationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValueNotFoundException.class})
    public ResponseEntity<?> handleValueNotFoundException(ValueNotFoundException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
