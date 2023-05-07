package com.neo.neouserservice.user.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
public class User extends BaseEntity implements UserDetails, Serializable {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private GenderEnum gender;
    @Column(length = 512)
    private String bio;
    private String passportCode;
    private String profileImage;
    private String mobile;
    @Column(unique=true)
    private String email;
    private String zipCode;
    private String city;
    private String country;
    private String address;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
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
}
