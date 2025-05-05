package com.example.dms.infrastructure.security.service;

import com.example.dms.infrastructure.security.domain.UserEntity;
import com.example.dms.infrastructure.security.jwt.data.MyUserDetails;
import com.example.dms.infrastructure.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new MyUserDetails(user);
    }
}
