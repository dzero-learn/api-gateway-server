package com.yam.apigateway.controller;

import com.yam.apigateway.common.ApiResponse;
import com.yam.apigateway.dto.UserRequest;
import com.yam.apigateway.dto.UserResponse;
import com.yam.apigateway.exception.UserIdNotFoundException;
import com.yam.apigateway.repository.UserRepository;
import com.yam.apigateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.success(userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userRepository.findById(id).map(UserResponse::from).orElseThrow(UserIdNotFoundException::new)));
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(authService.save(request)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("delete complete!"));
    }
}
