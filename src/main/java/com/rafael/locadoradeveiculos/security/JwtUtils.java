package com.rafael.locadoradeveiculos.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;

public class JwtUtils {

    private static final long EXPIRATION_TIME = 5 * 60 * 1000;
    private static final String SECRET = "R3diHOlrN?T?BFh7ng4Tl9PiM-YszaDMt";

    public static final String AUTH_HEADER = "Authorization";

    public static final String AUTH_BEARER = "Bearer ";

    public static String createToken(String email, List<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static List<String> getRoles(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);
    }

    public static String getUserEmail() {
        var userAuthToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return userAuthToken.getPrincipal().toString();
    }

}
