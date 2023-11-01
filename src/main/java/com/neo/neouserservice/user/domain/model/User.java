package com.neo.neouserservice.user.domain.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import com.neo.neouserservice.user.persistance.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class User extends BaseEntity implements UserDetails, Serializable, UserValidation {

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

    @Override
    public boolean isRegistrable() {
        // validation: local user exists
//        return !userRepository.existsByEmail(email);
        return true;
    }

    @Override
    public void isValid() {
        // validation: local user exists
        if (!this.isRegistrable()) {
            throw new IllegalArgumentException("User is not registrable");
        }
    }
}
