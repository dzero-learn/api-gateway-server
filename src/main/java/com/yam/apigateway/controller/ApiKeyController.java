package com.yam.apigateway.controller;

import com.yam.apigateway.dto.ApiKeyRequest;
import com.yam.apigateway.entity.ApiKey;
import com.yam.apigateway.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApiKeyController {
    private final ApiKeyRepository apiKeyRepository;

    @PostMapping("/api/keys")
    public ResponseEntity<?> addApiKey(@RequestBody ApiKeyRequest request) {
        // api 키 생성
        String uuid = UUID.randomUUID().toString();

//        ApiKey keys = new ApiKey(); // 이거 값 셋팅하는거 더 효율적인 방법 없나..
//        keys.setAppName(request.getAppName());
//        keys.setKeyValue(uuid);
//        keys.setActive(true);
//        keys.setCreateAt(LocalDateTime.now());

        try {
            apiKeyRepository.save(ApiKey.builder()
                    .appName(request.getAppName())
                    .keyValue(uuid)
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .build());
            return ResponseEntity.status(201).body(uuid); // 리소스 생성은 관례상 201을 보통 쓴다고함
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("키 저장 실패!"); // 보통 db crud 실패하면 상태값을 어케 떨궈준담?
        }
    }
}
