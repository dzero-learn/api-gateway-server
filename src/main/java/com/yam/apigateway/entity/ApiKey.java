package com.yam.apigateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "api_keys")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiKey {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique = true)
    private String keyValue; // 키값
    private String appName; // 키 사용처
    @Column(columnDefinition = "TINYINT(1)") // true=0x01 이렇게 표기돼서 명시
    private boolean active; // 키 유효여부
    private LocalDateTime createdAt; // 발급시각
}
