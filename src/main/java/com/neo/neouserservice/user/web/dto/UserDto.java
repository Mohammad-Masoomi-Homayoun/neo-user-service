package com.neo.neouserservice.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neo.neouserservice.common.enums.GenderEnum;
import com.neo.neouserservice.common.model.ID;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable{

    private ID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @NotEmpty
    private String email;
    private String password;

    public String getId() {
        return this.id.toString();
    }

}
