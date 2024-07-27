package com.example.factory.JWTToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${spring.token.jwt.secret.key}")
    private String SecretKeys;

    public String generateToken(String s) {
        Map<String, Object> claims = new HashMap<>();
        return create(s, claims);
    }

    private String create(String s, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(s)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 864000L * 60 * 60 * 10))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] key = Decoders.BASE64.decode(SecretKeys);
        return Keys.hmacShaKeyFor(key);
    }


    public boolean validateToken(String token ,UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !IsTokenExpired(token));
    }

    private Claims ExtractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T ExtractClaim(String token, Function<Claims, T> function) {
        Claims claims = ExtractAllClaim(token);
        return function.apply(claims);
    }

    public String getUsername(String token) {
        return ExtractClaim(token, Claims::getSubject);
    }
    private boolean IsTokenExpired(String token){
        return ExpectExpired(token).before(new Date());
    }

    private Date ExpectExpired(String token ){
        return ExtractClaim(token,Claims::getExpiration);
    }
}
