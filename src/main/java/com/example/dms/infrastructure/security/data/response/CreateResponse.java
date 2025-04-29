package com.example.dms.infrastructure.security.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateResponse {
    private String userName;
    private String role;
}
