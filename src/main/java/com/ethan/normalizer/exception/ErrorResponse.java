package com.ethan.normalizer.exception;

/**
 * Standard error response payload.
 *
 * @param code    a machine-readable error code (e.g. "MISSING_PARAM", "BLANK_VALUE")
 * @param message a human-readable description of what went wrong
 */
public record ErrorResponse(String code, String message) { }
