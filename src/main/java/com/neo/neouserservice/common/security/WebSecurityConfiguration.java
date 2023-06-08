package com.neo.neouserservice.common.security;

import com.neo.neouserservice.common.security.token.EmailPasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider;

    public WebSecurityConfiguration(EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider) {
        this.emailPasswordAuthenticationProvider = emailPasswordAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/user/register", "/login").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
        return authenticationManagerBuilder
                .authenticationProvider(emailPasswordAuthenticationProvider)
                .getOrBuild();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
