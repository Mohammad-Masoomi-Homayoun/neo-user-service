package com.neo.neouserservice.user.domain.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

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
