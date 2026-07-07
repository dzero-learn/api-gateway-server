package com.yam.apigateway.service;

import com.yam.apigateway.dto.LoginRequest;
import com.yam.apigateway.dto.LoginResponse;
import com.yam.apigateway.entity.User;
import com.yam.apigateway.repository.UserRepository;
import com.yam.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public LoginResponse loginCheck(LoginRequest request) {
        User user = userRepository.findUserByUsernameAndPassword(request.getUsername(), request.getPassword()).orElseThrow();

        // 토큰 생성
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

}
