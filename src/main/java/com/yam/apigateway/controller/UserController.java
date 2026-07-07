package com.yam.apigateway.controller;

import com.yam.apigateway.entity.User;
import com.yam.apigateway.repository.UserRepository;
import com.yam.apigateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getOne(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null); // ? 반환 어케해
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user).getBody();
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "deleted!";
    }
}
