package com.ling.userService.security.jwt;

import com.ling.userService.security.jwt.exception.InvalidTokenException;
import com.ling.userService.security.jwt.exception.InvalidTokenTypeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        try {

            if (!jwt.contains(".")) {
                throw new InvalidTokenException("Malformed token");
            }

            if (!jwtService.isAccessToken(jwt)) {
                throw new InvalidTokenTypeException("Token is not access token");
            }

            String userId = jwtService.extractUserId(jwt);

            if (userId == null) {
                throw new InvalidTokenException("UserId is null in token");
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                List.of()
                        );

                auth.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (ExpiredJwtException ex) {
            SecurityContextHolder.clearContext();
            throw new InvalidTokenException("Token expired");

        } catch (JwtException ex) {
            SecurityContextHolder.clearContext();
            throw new InvalidTokenException("Invalid JWT token");

        } catch (InvalidTokenTypeException | InvalidTokenException ex) {
            SecurityContextHolder.clearContext();
            throw ex;
        }

        filterChain.doFilter(request, response);
    }
}