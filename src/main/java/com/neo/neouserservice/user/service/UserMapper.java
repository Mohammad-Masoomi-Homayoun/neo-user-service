package com.neo.neouserservice.user.service;

import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDto toDto(User user);

    public abstract User toDomain(UserDto userDto);

}
