package com.neo.neouserservice.common.security;

import com.neo.neouserservice.common.execption.FilterChainExceptionHandler;
import com.neo.neouserservice.common.security.filter.JWTAuthenticationFilter;
import com.neo.neouserservice.common.security.filter.JWTAuthorizationFilter;
import com.neo.neouserservice.common.security.jwt.JwtUtil;
import com.neo.neouserservice.common.security.token.EmailPasswordAuthenticationProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider;
    private final FilterChainExceptionHandler filterChainExceptionHandler;
    private final ApplicationContext applicationContext;
    private final JwtUtil jwtUtil;

    public WebSecurityConfiguration(EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider, FilterChainExceptionHandler filterChainExceptionHandler, ApplicationContext applicationContext, JwtUtil jwtUtil) {
        this.emailPasswordAuthenticationProvider = emailPasswordAuthenticationProvider;
        this.filterChainExceptionHandler = filterChainExceptionHandler;
        this.applicationContext = applicationContext;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .cors().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/v3/**",
                    "/swagger-ui/**",
                    "/api/users/register",
                    "/api/users/login")
                .permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
            .addFilter(new JWTAuthenticationFilter(getAuthenticationManager(), jwtUtil))
            .addFilter(new JWTAuthorizationFilter(getAuthenticationManager(), jwtUtil));

        return http.build();
    }

    private AuthenticationManager getAuthenticationManager() {
        return applicationContext.getBean(AuthenticationManager.class);
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
