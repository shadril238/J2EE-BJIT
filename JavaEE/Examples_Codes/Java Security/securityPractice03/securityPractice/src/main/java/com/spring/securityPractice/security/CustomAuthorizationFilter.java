package com.spring.securityPractice.security;

import com.spring.securityPractice.constants.AppConstants;
import com.spring.securityPractice.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AppConstants.HEADER_STRING);
        if(header==null||!header.startsWith(AppConstants.TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
        }else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if(header != null){
            String token = header.replace(AppConstants.TOKEN_PREFIX,"");
            String user = JWTUtils.hasTokenExpired(token)? null : JWTUtils.extractUser(token);
            if(user!=null) return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
            return null;
        }
        return null;
    }
}
