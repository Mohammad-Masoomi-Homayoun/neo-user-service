package com.neo.neouserservice.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.ID;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable{

    private ID id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String bio;
    private String passportCode;
    private String profileImage;
    private String mobile;
    private String email;
    private String password;
    private String zipCode;
    private String city;
    private String country;
    private String address;

    public String getId() {
        return this.id.toString();
    }

}
