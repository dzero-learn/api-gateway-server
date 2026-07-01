package com.yam.apigateway.interceptor;

import com.yam.apigateway.entity.ApiKey;
import com.yam.apigateway.repository.ApiKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("X-API-Key");
        // 1. 헤더 자체가 없으면 차단
        if (apiKey == null) {
            response.setStatus(401);
            return false;
        }

        Optional<ApiKey> findApiKey = apiKeyRepository.findApiKeyByKeyValue(apiKey);
        // 엥 값이 없을 수도 있어서 Optional객체 사용
        if(findApiKey.isEmpty() || !findApiKey.get().isActive()) {
            response.setStatus(401);
            return false;
        } else {

            return true;
        }
    }
}
