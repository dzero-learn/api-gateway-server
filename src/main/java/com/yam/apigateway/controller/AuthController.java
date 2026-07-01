package com.yam.apigateway.controller;

import com.yam.apigateway.dto.LoginRequest;
import com.yam.apigateway.dto.LoginResponse;
import com.yam.apigateway.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // id+pwd 임시검증(db x)
        if(!request.getUsername().equals("yam") || !request.getPassword().equals("asdf")) {
            return ResponseEntity.status(401).body("인증 실패");
        }

        // 토큰 생성
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok().body(new LoginResponse(token));
    }
}
