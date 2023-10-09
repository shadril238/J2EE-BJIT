package com.example.recommendationservice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "MySecretMySecretMySecretMySecretMySecretMySecretMySecretMySecret";
    public static final long EXPIRATION_TIME = 864000000; //10 days

    public static final String SIGN_IN = "/users/login";
    public static final String SIGN_UP = "/users/register";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String USER_UNAUTHORIZED = "You are not authorized to access this!";
    public static final String USER_NOTFOUND = "User does not exists!";
    public static final String TOKEN_INVALID = "Token validation problem!";


}
