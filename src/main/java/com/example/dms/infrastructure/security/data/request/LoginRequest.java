package com.example.dms.infrastructure.security.data.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
