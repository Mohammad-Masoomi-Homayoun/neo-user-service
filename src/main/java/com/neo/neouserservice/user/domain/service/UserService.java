package com.neo.neouserservice.user.domain.service;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.persistance.repository.UserRepository;
import com.neo.neouserservice.user.web.client.UserWebClient;
import com.neo.neouserservice.user.web.dto.UserEntryResponseDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserWebClient userWebClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, UserWebClient userWebClient, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userWebClient = userWebClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserEntryResponseDto register(User user) {

        // validate by domain
        user.isValid();

        // validation: web user exists
        User webUser = userWebClient.getUser("http://localhost:8080/api/users/{id}", user.getId());
        if (webUser != null) {
            throw new IllegalArgumentException("id has already been taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserEntryResponseDto.builder()
                .token(jwtUtil.generateAccessToken(user))
                .refreshToke(jwtUtil.generateAccessRefreshToken(user))
                .expiration(jwtUtil.expiration().getEpochSecond())
                .build();
    }

    public User getUser(ID id) {
        return Optional.ofNullable(id)
                .map(uid -> userRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("id is required"));
    }

    public User getMyInfo() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal().toString();
        User user = userRepository.findUserByEmail(username);
        user.setPassword(null);
        return user;
    }
}
