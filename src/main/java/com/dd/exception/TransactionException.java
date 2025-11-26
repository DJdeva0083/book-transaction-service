package com.dd.exception;

import org.springframework.http.HttpStatus;


public class TransactionException extends RuntimeException {
    private final HttpStatus status;

    public TransactionException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}


