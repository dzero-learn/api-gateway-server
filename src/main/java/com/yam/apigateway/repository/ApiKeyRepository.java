package com.yam.apigateway.repository;

import com.yam.apigateway.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey,Long> {
    Optional<ApiKey> findApiKeyByKeyValue(String keyValue);
}
