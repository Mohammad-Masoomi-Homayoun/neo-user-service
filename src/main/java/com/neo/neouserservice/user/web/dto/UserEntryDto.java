package com.neo.neouserservice.user.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserEntryDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
