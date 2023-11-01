package com.neo.neouserservice.user.domain.mapper;

import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    public abstract User toDomain(UserDto userDto);

}
