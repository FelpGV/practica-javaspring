package com.practica1.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HandledErrorResponse> entityNotFoundException(EntityNotFoundException ex) {
        HandledErrorResponse errorResponse = new HandledErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HandledErrorResponse> elementAlreadyExistsException(DataIntegrityViolationException ex) {
        HandledErrorResponse errorResponse = new HandledErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<HandledErrorResponse> FieldEmptyException(NullPointerException ex) {
        HandledErrorResponse errorResponse = new HandledErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
