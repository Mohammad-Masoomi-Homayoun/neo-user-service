package com.neo.neouserservice.common.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${token.expire:3600}")
    private long expireTime;

    @Value("${token.key:password}")
    private String tokenKey;

    public String generateAccessToken(UserDetails user) {

        LocalDateTime expireAt = LocalDateTime.now().plus(expireTime, ChronoUnit.SECONDS);

        List<String> claims = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return JWT.create()
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .withExpiresAt(Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant()))
                .withClaim("scopes", "auth_scope")
                .withClaim("authorities", claims)
                .sign(Algorithm.HMAC512(tokenKey));
    }

    public String generateAccessRefreshToken(UserDetails user) {

        LocalDateTime expireAt = LocalDateTime.now().plus(expireTime, ChronoUnit.SECONDS);

        List<String> claims = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return JWT.create()
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .withExpiresAt(Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant()))
                .withClaim("scopes", "auth_scope")
                .withClaim("authorities", claims)
                .sign(Algorithm.HMAC512("password"));
    }

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC512(tokenKey)).build().verify(token);
    }
}
