package com.example.dms.infrastructure.security.mapping;

import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.response.CreateResponse;
import com.example.dms.infrastructure.security.data.response.LoginResponse;
import com.example.dms.infrastructure.security.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

    public static UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public static CreateResponse toCreateResponse(UserEntity userEntity){
        CreateResponse createResponse=new CreateResponse();
        createResponse.setUserName(userEntity.getUsername());
        createResponse.setRole(userEntity.getRole());
        return createResponse;
    }

    public static LoginResponse toLoginResponse(UserDetails userDetails){
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setUserName(userDetails.getUsername());
        loginResponse.setRole(userDetails.getAuthorities().stream().findFirst().get().toString());
        return loginResponse;
    }

}
