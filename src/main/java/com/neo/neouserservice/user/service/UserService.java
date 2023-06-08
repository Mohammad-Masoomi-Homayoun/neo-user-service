package com.neo.neouserservice.user.service;

import com.neo.neouserservice.common.execption.LoginFailedException;
import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import com.neo.neouserservice.user.dto.UserEntryDto;
import com.neo.neouserservice.user.dto.UserEntryResponseDto;
import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.service.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
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
                .expiration(jwtUtil.expiration().toEpochSecond(ZoneOffset.UTC))
                .build();
    }

    public User login(User user) {


        return user;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null)
            return null;

        Optional<User> userDetails = userRepository.findUserByEmail(username);

        if (!userDetails.isPresent())
            return null;

        return (UserDetails) userDetails.get();
    }

    public UserEntryDto login(UserEntryDto userEntryDto) {

        final UserDetails userDetails = loadUserByUsername(userEntryDto.getUsername());
        if (userDetails == null)
            throw new LoginFailedException("Can't find user with username: " + userEntryDto.getUsername());

//        authenticate(userEntryDto.getUsername(), userEntryDto.getPassword());

//        userEntryDto.setToken(jwtTokenUtil.generateToken(userDetails));
//        userEntryDto.setPassword(null);
        return userEntryDto;
    }

    public User getUser(ID id) {

        User user = userRepository.findById(id).get();

        if (user == null)
            throw new RuntimeException("User not found");

        return user;
    }


}
