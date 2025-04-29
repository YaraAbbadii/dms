package com.example.dms.infrastructure.security.service;

import com.example.dms.infrastructure.exception.UndefinedException;
import com.example.dms.infrastructure.security.domain.UserEntity;
import com.example.dms.infrastructure.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DmsUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(username);
        if(userEntity.isEmpty()){
            throw new UndefinedException("User not found");
        }
        return userEntity.get();
    }
}
