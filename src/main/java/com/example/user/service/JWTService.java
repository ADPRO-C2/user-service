package com.example.user.service;

import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String key;

    @Autowired
    private TokenRepository tokenRepository;

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSigninKey())
                .compact();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        Token findedToken = tokenRepository.findByJwtToken(token).orElse(null);
        boolean validToken = false;
        if (findedToken != null) {
            validToken = !findedToken.isLoggedOut();
        }
        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }

    public Token saveUserToken(User user) {
        String jwt = generateToken(user);
        Token token = new Token();
        token.setJwtToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
        return token;
    }

    public Token revokeTokenByUser(User user) {
        Token validTokens = tokenRepository.findByUser(user).orElse(null);
        if (validTokens == null) {
            return null;
        }
        tokenRepository.delete(validTokens);
        return validTokens;
    }

}
