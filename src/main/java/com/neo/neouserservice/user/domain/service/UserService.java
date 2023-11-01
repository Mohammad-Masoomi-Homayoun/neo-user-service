package com.neo.neouserservice.user.domain.service;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.persistance.repository.UserRepository;
import com.neo.neouserservice.user.web.dto.UserEntryResponseDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserEntryResponseDto register(User user) {

        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("email has already been taken");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        return UserEntryResponseDto.builder()
                .token(jwtUtil.generateAccessToken(user))
                .refreshToke(jwtUtil.generateAccessRefreshToken(user))
                .expiration(jwtUtil.expiration().getEpochSecond())
                .build();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(username)
                .map(u -> userRepository.findUserByEmail(u))
                .orElse(null);
    }

    public User getUser(ID id) {
        return Optional.ofNullable(id)
                .map(uid -> userRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("id is required"));
    }

    public User getMyInfo() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();
        User user = userRepository.findUserByEmail(username);
        user.setPassword(null);
        return user;
    }
}
