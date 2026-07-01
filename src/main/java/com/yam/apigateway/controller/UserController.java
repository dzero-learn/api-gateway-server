package com.yam.apigateway.controller;

import com.yam.apigateway.entity.User;
import com.yam.apigateway.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "deleted!";
    }
}
