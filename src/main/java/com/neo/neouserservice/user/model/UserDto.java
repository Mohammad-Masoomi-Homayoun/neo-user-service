package com.neo.neouserservice.user.model;

import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.ID;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserDto  implements Serializable{

    private ID id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private GenderEnum gender;
    private String bio;
    private String passportCode;
    private String profileImage;
    private String mobile;
    private String email;
    private String zipCode;
    private String city;
    private String country;
    private String address;

}
