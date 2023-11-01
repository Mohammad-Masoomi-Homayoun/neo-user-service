package com.neo.neouserservice.user.web.controller;


import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.web.dto.UserDto;
import com.neo.neouserservice.user.web.dto.UserEntryResponseDto;
import com.neo.neouserservice.user.domain.mapper.UserMapper;
import com.neo.neouserservice.user.domain.service.UserService;
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
    public UserEntryResponseDto register(@RequestBody UserDto userDto) {

        return service.register(mapper.toDomain(userDto));
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable("id") String id) {

        return mapper.toDto(service.getUser(ID.of(id)));
    }

    @GetMapping("/me")
    public UserDto getMyInfo() {

        return mapper.toDto(service.getMyInfo());
    }
}
