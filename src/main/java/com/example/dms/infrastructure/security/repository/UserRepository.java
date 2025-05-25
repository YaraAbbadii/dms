package com.example.dms.infrastructure.security.repository;

import com.example.dms.infrastructure.security.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUserName(String userName);

//    User findByUsername(String username);
//    boolean existsByUsername(String username);

}
