package com.itacademy.finalprod.project_.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    // Извлекаем токен из заголовка Authorization
    String token = extractToken(request);

    if (token != null && jwtUtil.validateToken(token)) {
        // Извлекаем имя пользователя из токена
        String username = jwtUtil.extractUsername(token);

        // Создаем объект аутентификации
        JwtAuthentication authentication = new JwtAuthentication(username);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Устанавливаем аутентификацию в контекст безопасности
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
}

private String extractToken(HttpServletRequest request) {
    // Ищем токен в заголовке Authorization
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
        return header.substring(7);  // Убираем префикс "Bearer "
    }
    return null;
    }
}

