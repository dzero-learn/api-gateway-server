package com.yam.apigateway.exception;

import com.yam.apigateway.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(ApiKeyNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleApiKeyNotFound(ApiKeyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Key Not Found."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.error("Internal Server Error."));
    }
}
