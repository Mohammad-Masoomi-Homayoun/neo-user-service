package com.neo.neouserservice.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import com.neo.neouserservice.common.security.token.EmailPasswordAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil2) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtTokenUtil2;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {
            Map<?, ?> creds = objectMapper.readValue(request.getInputStream(), Map.class);
            Authentication authentication = new EmailPasswordAuthenticationToken(creds.get("email"), creds.get("password"));
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BadCredentialsException("Authentication failed");
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("token", jwtUtil.generateAccessToken((UserDetails) authentication.getPrincipal()));
        body.put("refresh_token", jwtUtil.generateAccessRefreshToken((UserDetails) authentication.getPrincipal()));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), body);
    }
}
