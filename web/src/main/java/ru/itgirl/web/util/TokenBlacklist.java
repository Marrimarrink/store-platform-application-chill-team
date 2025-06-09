package ru.itgirl.web.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {
    private final ConcurrentHashMap<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    public void addToken(String token, long expiryTime) {
        blacklistedTokens.put(token, expiryTime);
    }

    public boolean isTokenBlacklisted(String token) {
        Long expiryTime = blacklistedTokens.get(token);
        if (expiryTime == null) {
            return false;
        }
        if (System.currentTimeMillis() > expiryTime) {
            blacklistedTokens.remove(token);
            return false;
        }
        return true;
    }
}