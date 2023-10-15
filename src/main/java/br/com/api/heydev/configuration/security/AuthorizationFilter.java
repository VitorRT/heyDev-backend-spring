package br.com.api.heydev.configuration.security;

import br.com.api.heydev.database.entity.UserEntity;
import br.com.api.heydev.service.ITokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private ITokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);
        if(token != null) {
            UserEntity user = tokenService.getUserEntityByToken(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
