package com.elgris.usersapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.Base64;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String authHeader = request.getHeader("Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        
        try {
            byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
            Claims claims = Jwts.parser()
                .verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseSignedClaims(token)
                .getPayload();
            
            request.setAttribute("claims", claims);
        } catch (SignatureException e) {
            throw new ServletException("Invalid token signature");
        }

        chain.doFilter(req, res);
    }
}