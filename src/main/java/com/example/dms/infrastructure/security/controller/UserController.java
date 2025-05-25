package com.example.dms.infrastructure.security.controller;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.request.LoginRequest;
import com.example.dms.infrastructure.security.jwt.data.JwtUtil;
import com.example.dms.infrastructure.security.service.UserService;
import com.example.dms.infrastructure.security.service.RateLimiterService; // <-- add this import
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
    private final RateLimiterService rateLimiterService;  // <-- Inject RateLimiterService

    private boolean tryConsume(String apiKey) {
        Bucket bucket = rateLimiterService.resolveBucket(apiKey);
        return bucket.tryConsume(1);
    }

    // Helper method to extract API key from header or fallback key
    private String getApiKeyOrDefault(String apiKey) {
        return apiKey != null && !apiKey.isEmpty() ? apiKey : "anonymous";
    }

    @GetMapping("/all")
    public ResponseEntity<?> allUsers(@RequestHeader(value = "X-API-Key", required = false) String apiKey) {
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.allUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@RequestHeader(value = "X-API-Key", required = false) String apiKey,
                                         @PathVariable Long id) {
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestHeader(value = "X-API-Key", required = false) String apiKey,
                                        @Valid @RequestBody UserDTO userDTO) {
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestHeader(value = "X-API-Key", required = false) String apiKey,
                                        @PathVariable Long id,
                                        @Valid @RequestBody UserDTO userDTO) {
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestHeader(value = "X-API-Key", required = false) String apiKey,
                                        @PathVariable Long id) {
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(value = "X-API-Key", required = false) String apiKey,
                                   @RequestBody LoginRequest loginRequest) {
        // You might want different limits here or no limits, adjust as needed
        if (!tryConsume(getApiKeyOrDefault(apiKey))) {
            return ResponseEntity.status(429).body("Too Many Requests - Rate limit exceeded.");
        }
        GeneralResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
