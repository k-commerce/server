package com.kcommerce.global.config.security;

public class JwtProperties {

    public static final long ACCESS_TOKEN_TIME = 1000L * 60 * 30; // 30 minutes

    public static final String BEARER_PREFIX = "Bearer ";

    public static final long REFRESH_TOKEN_TIME = 1000L * 60 * 60 * 24 * 7 * 2; // 2 weeks

    public static final String REFRESH_TOKEN_COOKIE = "REFRESH_TOKEN";
}
