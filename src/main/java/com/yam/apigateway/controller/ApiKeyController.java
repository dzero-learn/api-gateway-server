package com.yam.apigateway.controller;

import com.yam.apigateway.common.ApiResponse;
import com.yam.apigateway.dto.ApiKeyRequest;
import com.yam.apigateway.dto.ApiKeyResponse;
import com.yam.apigateway.entity.ApiKey;
import com.yam.apigateway.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiKeyController {
    private final ApiKeyService apiKeyService;
    @PostMapping("/api/keys")
    public ResponseEntity<?> addApiKey(@RequestBody ApiKeyRequest request) {
        // api키 발급
        ApiKey created = apiKeyService.createApiKey(request.getAppName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiKeyResponse.from(created)));
    }
}
