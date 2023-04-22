package com.neo.neouserservice.user.model;

import com.neo.neouserservice.common.GenderEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

@Data
public class User implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();
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
