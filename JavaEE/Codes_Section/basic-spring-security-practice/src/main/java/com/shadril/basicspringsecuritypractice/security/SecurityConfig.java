package com.shadril.basicspringsecuritypractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->{
                    auth
                            .requestMatchers(HttpMethod.GET, "/users/hello").hasRole("user")
                            .requestMatchers(HttpMethod.GET, "/users/**").hasRole("admin")
                            .anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                })
                ;
        return http.build();
    }
}
