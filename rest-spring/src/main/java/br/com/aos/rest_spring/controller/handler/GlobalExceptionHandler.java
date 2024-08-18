package br.com.aos.rest_spring.controller.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.aos.rest_spring.controller.DTO.ApiResponseDTO;
import br.com.aos.rest_spring.controller.DTO.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleException(NoSuchElementException ex,
      HttpServletRequest requestHandler) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        ApiResponseDTO.error(null, ex.getMessage()));
  }

  @ExceptionHandler(InsufficientAuthenticationException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleException(InsufficientAuthenticationException e,
      HttpServletRequest request) {

    ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(null, e.getMessage());

    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleException(BadCredentialsException e,
      HttpServletRequest request) {
    ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(null, "Invalid email or password");

    return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleException(AuthenticationException e,
      HttpServletRequest request) {
    ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(null, "Invalid email or password");

    return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseDTO<?>> handleValidationExceptions(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(
        new ErrorResponseDTO(errors),
        "Request contains invalid fields");

    return ResponseEntity.unprocessableEntity().body(apiResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDTO<?>> handleException(Exception e) {

    ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(null, e.getMessage());

    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
