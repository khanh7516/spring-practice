package com.example.springbootfirstapp.response;

import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationErrorResponse {
    private List<FieldError> errors;

    public ValidationErrorResponse(List<FieldError> errors) {
        this.errors = errors;
    }
}

