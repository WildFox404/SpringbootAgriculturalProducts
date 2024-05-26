package com.cow.spring_vue.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil{
    private static final String KEY="LAZYDOG";

    public static String getToken(Map<String,Object> claims){
        Date expirationTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12);
        return JWT.create()
                .withClaim("claims",claims)
                .withExpiresAt(expirationTime)
                .sign(Algorithm.HMAC256(KEY));
    }

    public static Map<String,Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
