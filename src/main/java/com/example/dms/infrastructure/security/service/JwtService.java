package com.example.dms.infrastructure.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret}")
    private String secret;  // Ensure the path is correct

    @Value("${spring.security.jwt.expirationMs}")
    private Long expirationMs;


    /**
     * Generates a JWT token based on the username.
     *
     * @param username Username of the user.
     * @return A JWT token.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Correct algorithm for HMAC with secret key
                .compact();
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Checks if the token is valid by verifying its claims.
     *
     * @param token The JWT token.
     * @return Whether the token is valid or not.
     */
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // This will throw an exception if the token is invalid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())  // Using the correct signing key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Provides the signing key to be used in signing and verifying JWTs.
     *
     * @return The signing key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);  // Correct HMAC signing key for HS256 algorithm
    }
}
