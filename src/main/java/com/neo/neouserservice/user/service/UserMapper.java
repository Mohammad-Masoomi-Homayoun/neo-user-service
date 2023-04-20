package com.neo.neouserservice.user.service;

import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserDto userDto) {

        if(userDto == null)
            return null;

        User user = new User();
        user.setName(userDto.getName());

        return user;
    }

    public UserDto toDto(User user) {

        if(user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());

        return userDto;
    }
}
