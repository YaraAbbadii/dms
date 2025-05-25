package com.example.dms.infrastructure.security.jwt.data;

import io.github.bucket4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private final List<String> whiteList = List.of("/users/login", "/users/create", "/auth");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return whiteList.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String userKey = extractUserIdentifier(); // fallback to IP if no user
        if (userKey == null) {
            userKey = request.getRemoteAddr();
        }

        Bucket bucket = buckets.computeIfAbsent(userKey, k -> createNewBucket());

        var bucketConsume= bucket.tryConsume(1);
        if (bucketConsume) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded. Try again later.");
        }
    }

    private String extractUserIdentifier() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername(); // Or user ID if you have a custom UserDetails
        }
        return null;
    }

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.simple(5, Duration.ofMinutes(1)); // 5 requests per minute
        return Bucket4j.builder().addLimit(limit).build();
    }
}
