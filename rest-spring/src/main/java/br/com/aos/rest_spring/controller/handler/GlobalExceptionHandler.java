package br.com.aos.rest_spring.controller.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ExceptionResponse> handleException(NoSuchElementException ex,
      HttpServletRequest requestHandler) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        ExceptionResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .timestamp(LocalDateTime.now())
            .path(requestHandler.getServletPath())
            .error(ex.getMessage())
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex, HttpServletRequest requestHandler) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.badRequest().body(
        ExceptionResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .timestamp(LocalDateTime.now())
            .path(requestHandler.getServletPath())
            .error("Bad Request")
            .data(errors)
            .build());

  }
}
