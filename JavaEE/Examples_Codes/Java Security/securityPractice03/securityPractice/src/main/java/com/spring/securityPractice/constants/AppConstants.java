package com.spring.securityPractice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "MySecretMySecretMySecretMySecretMySecretMySecretMySecretMySecret";
    public static final long EXPIRATION_TIME = 864000000; //10 days

    public static final String SIGN_IN = "/login";
    public static final String SIGN_UP = "/users/registration";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
