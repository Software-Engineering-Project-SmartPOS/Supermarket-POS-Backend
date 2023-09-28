package com.SupermarketPOS.Backend.Config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    private String SECRET = "RyBaEtTM/eBPnZS2wQDFBe+ErrbnE5Ur8MylzQN8WdMvylcQFCf2KO/LMZccJkGr";
    private long expiration;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims , username);
    }


    public String createToken(Map<String, Object> claims, String username ){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expiration) )
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
