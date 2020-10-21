package com.wusd.familyfinanceapi.util;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.util.Date;

public class JwtUtils {
    private final static String secret = "Wusd123..";

    public static String encrypt(String str) {
        String myJwtSecret = Jwts.builder()
                .setSubject(str)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
        return myJwtSecret;
    }

    public static String decrypt(String str) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(str)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        Date expiration = body.getExpiration();
        if (expiration.compareTo(new Date()) < 0) throw new AuthenticationServiceException("凭证已过期, 请重新登录!");
        String subject = body.getSubject();
        return subject;
    }

    public static void main(String[] args) {
    }
}
