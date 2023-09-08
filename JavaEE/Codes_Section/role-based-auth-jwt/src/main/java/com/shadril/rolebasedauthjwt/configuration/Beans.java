package com.shadril.rolebasedauthjwt.configuration;

import com.shadril.rolebasedauthjwt.security.jwt.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Beans {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationTokenFilter authTokenFilter(){
        return new AuthenticationTokenFilter();
    }


}
