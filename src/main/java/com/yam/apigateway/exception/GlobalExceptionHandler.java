package com.yam.apigateway.exception;

import com.yam.apigateway.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ApiKeyNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleApiKeyNotFound(ApiKeyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Key Not Found."));
    }

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<ApiResponse<?>> handleLoginFail(LoginFailException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error("Login Fail."));
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserIdNotFound(UserIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("UserId Not Found."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Internal Server Error."));
    }
}
