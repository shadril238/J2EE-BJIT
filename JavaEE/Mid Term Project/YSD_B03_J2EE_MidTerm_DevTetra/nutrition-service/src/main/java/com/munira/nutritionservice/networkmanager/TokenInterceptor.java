package com.munira.nutritionservice.networkmanager;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwtToken = retrieveJwtToken();

        if (jwtToken != null && !jwtToken.isEmpty()) {
            requestTemplate.header("Authorization", "Bearer " + jwtToken);
        }
    }
    private String retrieveJwtToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}
