package com.yam.apigateway.dto;

import com.yam.apigateway.entity.ApiKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiKeyResponse {
    private String keyValue; // 키값
    private String appName;
    private boolean active; // 키 유효여부
    private LocalDateTime createdAt; // 발급시각

    public static ApiKeyResponse from(ApiKey apiKey) {
        return ApiKeyResponse.builder()
                .appName(apiKey.getAppName())
                .keyValue(apiKey.getKeyValue())
                .active(apiKey.isActive())
                .createdAt(apiKey.getCreatedAt())
                .build();
    }
}
