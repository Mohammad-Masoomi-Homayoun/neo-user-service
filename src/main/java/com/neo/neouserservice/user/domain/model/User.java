package com.neo.neouserservice.user.domain.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class User extends BaseEntity implements UserDetails, Serializable {

    private String name;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @Column(unique=true)
    private String email;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static User of(String username) {
        User user = new User();
        user.setEmail(username);
        return user;
    }
}
