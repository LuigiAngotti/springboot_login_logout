package com.twd.SpringSecurityJWT.logout;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class BlackList {
    private Set<String> validTokens = new HashSet<>();

    public void invalidateToken(String token) {
        validTokens.remove(token);
    }

    public void addValidToken(String token) {
        validTokens.add(token);
    }

    public boolean isTokenValid(String token) {
        return validTokens.contains(token);
    }
}
