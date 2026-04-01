package com.bugtracker.bugtracker_backend.controller;

import com.bugtracker.bugtracker_backend.entity.User;
import com.bugtracker.bugtracker_backend.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        return userService.login(username, password)
                .map(user -> ResponseEntity.ok(Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "username", user.getUsername(),
                        "role", user.getRole()
                )))
                .orElse(ResponseEntity.status(401)
                        .body(Map.of("error", "Invalid username or password")));
    }

    @GetMapping("/users")
    public List<Map<String, Object>> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> Map.<String, Object>of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "username", user.getUsername(),
                        "role", user.getRole()
                ))
                .collect(Collectors.toList());
    }
}
