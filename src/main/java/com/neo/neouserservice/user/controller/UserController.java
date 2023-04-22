package com.neo.neouserservice.user.controller;


import com.neo.neouserservice.user.model.UserDto;
import com.neo.neouserservice.user.service.UserMapper;
import com.neo.neouserservice.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("register")
    public UserDto register(@RequestBody UserDto userDto) {

        return mapper.toDto(service.register(mapper.toDomain(userDto)));
    }

    @PostMapping("login")
    public UserDto login(@RequestBody UserDto userDto) {

        return mapper.toDto(service.login(mapper.toDomain(userDto)));
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable("id") String id) {

        return mapper.toDto(service.getUser(id));
    }
}