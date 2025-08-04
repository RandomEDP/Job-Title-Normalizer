package com.ethan.normalizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MissingServletRequestParameterException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * Global exception handler to return custom JSON errors
 * for missing request parameters and validation failures.
 */
@RestControllerAdvice
public class ApiErrorHandler {

    /**
     * Catches requests where a required @RequestParam is absent.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParam(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String msg  = String.format("Required parameter '%s' is missing", name);
        return new ErrorResponse("MISSING_PARAM", msg);
    }

    /**
     * Catches validation failures (e.g. blank title via @NotBlank).
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBlankValue(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Invalid input");
        return new ErrorResponse("BLANK_VALUE", message);
    }
}
