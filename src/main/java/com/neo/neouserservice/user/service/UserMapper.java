package com.neo.neouserservice.user.service;

import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    public abstract User toDomain(UserDto userDto);

}
