package com.neo.neouserservice.user.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Data
@Entity
public class User extends BaseEntity implements Serializable {

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

}
