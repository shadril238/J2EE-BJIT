package com.shadril.onlinebooklibraryapplication.security;

import com.shadril.onlinebooklibraryapplication.SpringApplicationContext;
import com.shadril.onlinebooklibraryapplication.constants.AppConstants;
import com.shadril.onlinebooklibraryapplication.service.UserService;
import com.shadril.onlinebooklibraryapplication.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
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
    public UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if(header != null){
            String token = header.replace(AppConstants.TOKEN_PREFIX,"");
            String user = JWTUtils.hasTokenExpired(token)? null : JWTUtils.extractUser(token);
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user != null) {
                UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImplementation");
                String userRole = userService.getUser(user).getRole();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole));
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        }
        return null;
    }
}
