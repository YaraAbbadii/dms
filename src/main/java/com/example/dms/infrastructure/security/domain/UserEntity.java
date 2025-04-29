package com.example.dms.infrastructure.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "USERS")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeq")
    @SequenceGenerator(name = "userIdSeq", sequenceName = "userIdSeq", allocationSize = 1)
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String role;

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        return grantedAuthorityList;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
