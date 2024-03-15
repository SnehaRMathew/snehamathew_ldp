package com.zemoso.checkr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = NoSuchCandidateExistsException.class)
    public ResponseEntity<Object> noSuchCandidateExists(NoSuchCandidateExistsException ex) {
        return new ResponseEntity<>("No such customer exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = NoSuchHRExistsException.class)
    public ResponseEntity<Object> noSuchHRExists(NoSuchHRExistsException ex) {
        return new ResponseEntity<>("No such HR exist", HttpStatus.NOT_FOUND);

    }
}
