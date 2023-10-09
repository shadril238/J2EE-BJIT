package com.nuri.socialservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({GroupNotMatchException.class})
    public ResponseEntity<?> handleGroupNotMatchException(GroupNotMatchException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NameAlreadyExistsException.class})
    public ResponseEntity<?> handleNameAlreadyExistsException(NameAlreadyExistsException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullValueException.class})
    public ResponseEntity<?> handleNullValueException(NullValueException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ValueNotFoundException.class})
    public ResponseEntity<?> handleValueNotFoundException(ValueNotFoundException MESSAGE) {
        return new ResponseEntity<>(MESSAGE.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
