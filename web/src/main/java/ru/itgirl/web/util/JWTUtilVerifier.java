package ru.itgirl.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtilVerifier {

    @Value("${jwt.secret}")
    private String secret;

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw expiredJwtException;
        } catch (Exception e) {
            return null;
        }
    }
    public String extractRole(String token) {
        Claims claims = validateToken(token);
        return claims != null ? claims.get("role", String.class) : null;
    }
    public boolean isTokenExpired(String token) {
        try {
            validateToken(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}