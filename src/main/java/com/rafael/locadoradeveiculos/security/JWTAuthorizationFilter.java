package com.rafael.locadoradeveiculos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        var token = request.getHeader(JwtUtils.AUTH_HEADER);

        if(token == null || ! token.startsWith(JwtUtils.AUTH_BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        token = token.replace(JwtUtils.AUTH_BEARER, "");
        var email = JwtUtils.getEmail(token);
        var roles = JwtUtils.getRoles(token);

        var authUserToken = new UsernamePasswordAuthenticationToken(
                email,
                null,
                List.of(new SimpleGrantedAuthority(roles.get(0)))
        );

        SecurityContextHolder.getContext().setAuthentication(authUserToken);

        chain.doFilter(request, response);

    }
}
