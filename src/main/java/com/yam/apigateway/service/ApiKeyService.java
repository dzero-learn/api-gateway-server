package com.yam.apigateway.service;

import com.yam.apigateway.entity.ApiKey;
import com.yam.apigateway.exception.ApiKeyNotFoundException;
import com.yam.apigateway.exception.GlobalExceptionHandler;
import com.yam.apigateway.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;
    private static final SecureRandom secureRandom = new SecureRandom();

    @ExceptionHandler(GlobalExceptionHandler.class)
    public ApiKey createApiKey(String appName) {
        // api 키 생성
        byte[] buffer = new byte[32]; // 32바이트=256비트 배열

        secureRandom.nextBytes(buffer); // ? 반환이 void인데? 알아서 배열에 채워주네
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer);

        return apiKeyRepository.save(ApiKey.builder()
                .appName(appName)
                .keyValue(encoded)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build());
    }
}
