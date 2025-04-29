package com.example.dms.infrastructure.security.data.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String userName;
    private String role;
}
