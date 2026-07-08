package com.yam.apigateway.interceptor;

import com.yam.apigateway.entity.ApiKey;
import com.yam.apigateway.exception.ApiKeyNotFoundException;
import com.yam.apigateway.repository.ApiKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("X-API-Key");
        // 1. 헤더 자체가 없으면 차단
        if (apiKey == null)
            throw new ApiKeyNotFoundException("헤더에 키 없음.");

        ApiKey foundKey = apiKeyRepository.findApiKeyByKeyValue(apiKey).orElseThrow(() -> new ApiKeyNotFoundException("유효하지 않은 키."));
        if(!foundKey.isActive())
            throw new ApiKeyNotFoundException("비활성화 키.");

        return true;
    }
}
