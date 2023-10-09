package com.example.recommendationservice.networkmanager;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public TokenInterceptor customtokenInterceptor() {
        return new TokenInterceptor();
    }
}
