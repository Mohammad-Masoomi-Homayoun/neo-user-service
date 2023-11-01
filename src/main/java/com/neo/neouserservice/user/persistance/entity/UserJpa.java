package com.neo.neouserservice.user.persistance.entity;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import com.neo.neouserservice.user.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "neo_user")
public class UserJpa extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    // validation
    @Column(unique=true)
    private String email;
    private String password;

    public static User of(String username) {
        User user = new User();
        user.setEmail(username);
        return user;
    }
}
