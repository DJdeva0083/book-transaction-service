package com.dd.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.dd.entity.ErrorDetails;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.*;
@RestControllerAdvice
public class GlobalExceptionHandler {

      @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorDetails> handleBookException(BookException ex, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Book Error",
            ex.getMessage(),
            request.getRequestURI(), null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                                .collect(Collectors.joining(", "));
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            errorMessage,
            request.getRequestURI(), errorMessage
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Catch-all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getRequestURI(), null
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
