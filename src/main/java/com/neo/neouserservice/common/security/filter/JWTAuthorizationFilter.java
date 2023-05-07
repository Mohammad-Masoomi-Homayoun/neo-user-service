package com.neo.neouserservice.common.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil2) {
        super(authenticationManager);
        this.jwtUtil = jwtTokenUtil2;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken userToken = extractUserToke(token);

        if(userToken != null)
            SecurityContextHolder.getContext().setAuthentication(userToken);
        chain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken extractUserToke(String token) {

        DecodedJWT decodedJWT = jwtUtil.decode(token);
        if(!Arrays.asList(decodedJWT.getClaim("scopes")).contains("auth_scope")) {
            return null;
        }

        String userEmail = decodedJWT.getSubject();
        List<GrantedAuthority> authorities = new ArrayList<>();
        decodedJWT.getClaim("authorities").as(List.class)
                .forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.toString())));


        if(userEmail != null)
            return new UsernamePasswordAuthenticationToken(userEmail, null, authorities);
        return null;
    }
}
