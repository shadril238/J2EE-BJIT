package com.shadril.onlinebooklibraryapplication.security;


import com.shadril.onlinebooklibraryapplication.constants.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers(HttpMethod.POST, AppConstants.SIGN_IN,AppConstants.SIGN_UP).permitAll()
                            .requestMatchers(HttpMethod.GET,"/books/all").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/books/create").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/books/update").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,"/books/delete").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/users/{userId}").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/books/{bookId}/borrow").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/users/{userId}/books").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/books/{bookId}/return").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/users/{userId}/borrowed-books").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/users/{userId}/history").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/books/{bookId}/reviews/create").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.PUT,"/books/{bookId}/reviews/{reviewId}/update").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.DELETE,"/books/{bookId}/reviews/{reviewId}/delete").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/books/{bookId}/reserve").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.POST,"/books/{bookId}/cancel-reservation").hasRole("CUSTOMER")
                            .requestMatchers(HttpMethod.GET,"/books/{bookId}/reviews").hasRole("CUSTOMER")
                            .anyRequest().permitAll();
                })
                .addFilter(new CustomAuthenticationFilter(authenticationManager))
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }



}
