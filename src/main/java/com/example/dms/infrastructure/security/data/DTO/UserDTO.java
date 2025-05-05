package com.example.dms.infrastructure.security.data.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String role;
 }

