package com.example.dms.infrastructure.security.jwt.data;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "7b0e7c1dc5411cb049f4f84e2c0d7ebbf1b1dca311c8e3311be9057282f7292f48600a6f9c458e65c1428ef0cef9a617c2a84cc3e3a6fbe46b80bf39f096093c44c620c8623377d8e58e60fe5700ba63eb13edf8773540d5a08bfc8e84545144348af6494864b6be7ee507c96e3a738afbcdc7a9147ee07c90f5370b151c1f5e77c017a373aa6cceeac9a8b661986a866bb44d8f263cff93f9aebfb300db1a761d9c098f0d1e2a0494776044e3994d1214d1cb734fb41d50c1296516b2e6e84e542cd9e61e5f31e614b0a2239c5c58d95b131c38b0ef91edd1e0a0ca10cd0c262086fcb16dfeed5d99247f32fa61fb3ffde097b9d41335d6f9eb66dd0ef10361";
    private SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    private final static long EXPIRATION_TIME = 3600000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }


    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
