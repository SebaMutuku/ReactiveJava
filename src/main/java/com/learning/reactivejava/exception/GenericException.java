package com.learning.reactivejava.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends Exception {
    private final String message;
    private final HttpStatus httpStatus;

    public GenericException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public GenericException(String message) {
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
