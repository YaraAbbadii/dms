package com.example.dms.infrastructure.security.service;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.exception.UndefinedException;
import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.request.LoginRequest;
import com.example.dms.infrastructure.security.data.response.CreateResponse;
import com.example.dms.infrastructure.security.data.response.LoginResponse;
import com.example.dms.infrastructure.security.domain.UserEntity;
import com.example.dms.infrastructure.security.mapping.UserMapper;
import com.example.dms.infrastructure.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DmsUserDetailsService dmsUserDetailsService;

    public GeneralResponse allUsers() {
        return new GeneralResponse().success(userRepository.findAll());
    }

    public GeneralResponse getUserById(Long id) {
        return new GeneralResponse().success(userRepository.findById(id));
    }

    public GeneralResponse login(LoginRequest loginRequest) {
        UserDetails userDetails = dmsUserDetailsService.loadUserByUsername(loginRequest.getUserName());
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new UndefinedException("Bad credentials");
        }
        LoginResponse loginResponse = UserMapper.toLoginResponse(userDetails);
        return new GeneralResponse().success(loginResponse);
    }

    public GeneralResponse createUser(UserDTO userDTO) {
        UserEntity entity = UserMapper.toUserEntity(userDTO);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        CreateResponse createResponse = UserMapper.toCreateResponse(entity);
        return new GeneralResponse().success(createResponse);
    }

    public GeneralResponse updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(userUpdated -> {
            userUpdated.setId(userDTO.getId());
            userUpdated.setUserName(userDTO.getUserName());
            userUpdated.setPassword(userDTO.getPassword());
            userUpdated.setRole(userDTO.getRole());
            userRepository.save(userUpdated);
            return new GeneralResponse().success(userUpdated);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        userRepository.delete(userEntity);
    }

}
