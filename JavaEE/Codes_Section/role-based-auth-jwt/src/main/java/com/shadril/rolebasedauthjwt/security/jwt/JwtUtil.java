package com.shadril.rolebasedauthjwt.security.jwt;


import com.shadril.rolebasedauthjwt.serviceImpl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public static final long TOKEN_EXPIRATION_TIME=1000*60*60*24;
    private static final String APPLICATION_SECRET_KEY = "c1527cd893c124773d811911970c8fe6e857d6df5dc9226bd8a160614c0cd963a4ddea2b94bb7d36021ef9d865d5cea294a82dd49a0bb269f51f6e7a57f79421";

    public String generateToken(Authentication authentication){

        UserDetailsImpl userDetails =(UserDetailsImpl)authentication.getPrincipal();

        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("roles",userDetails.getAuthorities());

        System.out.println("Line:29: JwtUtil class generateToken method");
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, APPLICATION_SECRET_KEY)
                .compact();


    }

    public String getUserNameFromJwtToken(String token) {
        System.out.println("Line:43: JwtUtil class getUserNameFromJwtToken method");

        return Jwts.parser().setSigningKey(APPLICATION_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        System.out.println("Line:29: JwtUtil class validateJwtToken method");

        try {
            Jwts.parser().setSigningKey(APPLICATION_SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }








}
