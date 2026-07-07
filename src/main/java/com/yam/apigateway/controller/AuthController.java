package com.yam.apigateway.controller;

import com.yam.apigateway.dto.LoginRequest;
import com.yam.apigateway.dto.LoginResponse;
import com.yam.apigateway.repository.UserRepository;
import com.yam.apigateway.service.UserService;
import com.yam.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // id+pwd 임시검증(db x)
        // login 체크
        LoginResponse response = userService.loginCheck(request);
        return ResponseEntity.ok().body(response.getToken());
    }
}
