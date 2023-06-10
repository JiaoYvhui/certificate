package com.manage.certificate.util;

/**
 * 存放 redis 的key
 */
public class RedisConstants {
    public static final String LOGIN_USER_KEY = "jwt:login:token:";
    public static final Long LOGIN_USER_TTL = 36000L;
}
