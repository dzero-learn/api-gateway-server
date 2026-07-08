package com.yam.apigateway.service;

import com.yam.apigateway.dto.LoginRequest;
import com.yam.apigateway.dto.LoginResponse;
import com.yam.apigateway.dto.UserRequest;
import com.yam.apigateway.dto.UserResponse;
import com.yam.apigateway.entity.User;
import com.yam.apigateway.exception.LoginFailException;
import com.yam.apigateway.repository.UserRepository;
import com.yam.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public LoginResponse loginCheck(LoginRequest request) {
        User user = userRepository.findUsersByUsername(request.getUsername()).orElseThrow(LoginFailException::new);

        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new LoginFailException();

        // 토큰 생성
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

    public UserResponse save(UserRequest request) {
        User user = request.toEntity();
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return UserResponse.from(userRepository.save(user));
    }
}
