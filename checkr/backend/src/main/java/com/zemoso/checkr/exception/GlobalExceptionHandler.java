package com.zemoso.checkr.exception;

import com.zemoso.checkr.controller.CandidateController;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final  Logger LOGGER = Logger.getLogger(CandidateController.class.getName());
    @ExceptionHandler(value
            = NoSuchCandidateExistsException.class)
    public ResponseEntity<Object> noSuchCandidateExists(NoSuchCandidateExistsException ex) {
        return new ResponseEntity<>("No such customer exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MappingException.class)
    public ResponseEntity<String> handleModelMapperException(MappingException ex) {
        LOGGER.log(Level.WARNING,ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during object mapping");
    }

    @ExceptionHandler(value = NoSuchChargeExistsException.class)
    public ResponseEntity<Object> noSuchChargeExists(NoSuchChargeExistsException ex) {
        return new ResponseEntity<>("No such charge exist", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
