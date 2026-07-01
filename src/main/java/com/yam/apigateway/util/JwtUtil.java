package com.yam.apigateway.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final Long expiration; // 토큰 유효시간, 고정값이라 final 사용
    private final SecretKey key; // 키 객체 생성, 고정값이라 final 사용

    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    public String generateToken(String username) {
        // 토큰 생성
        return Jwts.builder()
                .subject(username) // 받은 username 토큰 주입
                .issuedAt(new Date()) // 시작시간 토큰주입
                .expiration(new Date(System.currentTimeMillis() + expiration)) // 만료시간 토큰주입
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        // 토큰 파싱 후 username 구하기
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token) // 매개변수 string으로 써도 되는거?
                .getPayload()
                .getSubject(); // 토큰에 들어있는 username 뽑아옴
    }

    public boolean validateToken(String token) {
        // 토큰 유효성 체크
        try {
            Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token); // 요 메서드가 알아서 토큰파싱 하고 서명/만료 검증함
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}