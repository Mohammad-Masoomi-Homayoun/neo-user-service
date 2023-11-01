package com.neo.neouserservice.user.web.client;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.domain.mapper.UserMapper;
import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.web.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserWebClient {

    private final UserMapper userMapper;
    private final RestTemplate restTemplate;

    public UserWebClient(UserMapper userMapper) {
        this.userMapper = userMapper;
        this.restTemplate = new RestTemplate();
    }

    public User getUser(String url, ID id) {
        return userMapper.toDomain(restTemplate.getForObject(url, UserDto.class, id.toString()));
    }
}
