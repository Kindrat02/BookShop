package com.serve.mentorship.config;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<Object> handleNPE(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                "Error: " + ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Entity not found: " + ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                "Constraint has been violated: " + ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
