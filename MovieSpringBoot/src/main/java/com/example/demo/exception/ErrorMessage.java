package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private String message;
    private Date timestamp;
    private String description;

    public ErrorMessage(Integer statusCode, Set<ConstraintViolation<?>> constraintViolations, Date timestamp, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorMessage(Integer statusCode, String message, Date timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }
}