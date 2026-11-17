package com.example.tourist.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Value("${app.security.api-token}")
    private String validToken;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Protect API endpoints; leave Swagger UI and OpenAPI documentation accessible.
        return !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String suppliedToken = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            suppliedToken = authorization.substring(7).trim();
        }

        boolean valid = suppliedToken != null && !suppliedToken.isEmpty()
                && MessageDigest.isEqual(
                    suppliedToken.getBytes(StandardCharsets.UTF_8),
                    validToken.getBytes(StandardCharsets.UTF_8));

        if (!valid) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(
                "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Missing or invalid bearer token\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
