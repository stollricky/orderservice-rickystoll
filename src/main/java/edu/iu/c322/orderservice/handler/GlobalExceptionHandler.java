package edu.iu.c322.orderservice.handler;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exception) {
        String errorMessages = exception.getBindingResult().getFieldErrors().stream().map(error ->
                error.getDefaultMessage()).collect(Collectors.joining(",\n"));
        return ResponseEntity.badRequest().body(errorMessages);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
