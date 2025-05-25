package com.example.dms.infrastructure.security.service;

import com.example.dms.data.GeneralResponse;
import com.example.dms.infrastructure.exception.UndefinedException;
import com.example.dms.infrastructure.security.data.DTO.UserDTO;
import com.example.dms.infrastructure.security.data.request.LoginRequest;
import com.example.dms.infrastructure.security.data.response.CreateResponse;
import com.example.dms.infrastructure.security.data.response.LoginResponse;
import com.example.dms.infrastructure.security.domain.UserEntity;
import com.example.dms.infrastructure.security.jwt.data.JwtUtil;
import com.example.dms.infrastructure.security.mapping.UserMapper;
import com.example.dms.infrastructure.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public GeneralResponse allUsers() {
        return new GeneralResponse().success(userRepository.findAll());
    }

    public GeneralResponse getUserById(Long id) {
        return new GeneralResponse().success(userRepository.findById(id));
    }

    public GeneralResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwt);
        loginResponse.setUsername(userDetails.getUsername());
        loginResponse.setRole(userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER")); // default if no roles found
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
        return userRepository.findById(id).map(user -> {
            user.setUserName(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // encode updated password
            user.setRole(userDTO.getRole());

            userRepository.save(user);

            return new GeneralResponse().success(UserMapper.toCreateResponse(user));
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public GeneralResponse deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        userRepository.delete(user);

        return new GeneralResponse().success("User with ID " + id + " deleted successfully");
    }


}
