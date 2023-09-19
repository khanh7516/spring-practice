package com.example.imagemanagement.exception;


import com.example.imagemanagement.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}


