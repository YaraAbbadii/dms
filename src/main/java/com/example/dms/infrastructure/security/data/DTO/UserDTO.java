package com.example.dms.infrastructure.security.data.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private String role;

}
