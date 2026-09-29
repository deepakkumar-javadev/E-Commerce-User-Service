package com.deepak.UserService.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	// Secret key used to sign the JWT
    private final String SECRET_KEY =
            "mySecretKeyForMacyEcommerceApplication2026SecureKey";

    // =====================================================
    // 1. Generate JWT Token
    // =====================================================
    public String generateToken(String email, Long userId, String role) {

        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(getSignKey())
                .compact();
    }

    // =====================================================
    // 2. Get Email from JWT
    // =====================================================
    public String extractEmail(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    // =====================================================
    // 3. Get User ID from JWT
    // =====================================================
    public Long extractUserId(String token) {

        return extractAllClaims(token)
                .get("userId", Long.class);
    }

    // =====================================================
    // 4. Get Role from JWT
    // =====================================================
    public String extractRole(String token) {

        return extractAllClaims(token)
                .get("role", String.class);
    }

    // =====================================================
    // 5. Read all claims
    // =====================================================
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // =====================================================
    // 6. Create Secret Key
    // =====================================================
    private SecretKey getSignKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }
}
