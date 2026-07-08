package com.yam.apigateway.controller;

import com.yam.apigateway.common.ApiResponse;
import com.yam.apigateway.dto.LoginRequest;
import com.yam.apigateway.dto.LoginResponse;
import com.yam.apigateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // login 체크
        LoginResponse response = authService.loginCheck(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
