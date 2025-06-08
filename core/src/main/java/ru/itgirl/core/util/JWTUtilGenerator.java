package ru.itgirl.core.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itgirl.core.entity.User;

import java.util.Date;

@Component
public class JWTUtilGenerator {

    @Value("${jwt.secret}")
    private String secret; // Секрет извлекается из application.properties

    private static final long EXPIRATION_TIME = 86400000; // 1 день в миллисекундах

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setId(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}