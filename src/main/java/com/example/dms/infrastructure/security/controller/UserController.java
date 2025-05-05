package com.example.dms.infrastructure.security.controller;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.request.LoginRequest;
import com.example.dms.infrastructure.security.jwt.data.JwtUtil;
import com.example.dms.infrastructure.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @GetMapping("/all")
    public GeneralResponse allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public GeneralResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public GeneralResponse createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public GeneralResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')") // Optional: restrict delete access to admins
    @DeleteMapping("/{id}")
    public GeneralResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public GeneralResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
