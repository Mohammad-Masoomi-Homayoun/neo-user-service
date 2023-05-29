package com.neo.neouserservice.common.security.token;


import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {


    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public EmailPasswordAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<User> user = userRepository.findUserByEmail(authentication.getName());
        if(!user.isPresent())
            throw new BadCredentialsException("User not found");

        if(!passwordEncoder.matches(authentication.getPrincipal().toString(), user.get().getPassword()))
            throw new BadCredentialsException("Password is incorrect");

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(EmailPasswordAuthenticationToken.class);
    }
}