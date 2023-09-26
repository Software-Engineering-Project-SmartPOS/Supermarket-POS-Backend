package com.SupermarketPOS.Backend.Config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "Yc6L8rnWqb4ezx7bmoksxpSXmvlxkcBKb94xPN+ajTLRMGEaTXx69Ads/Hnw3NSp";
    private static final Integer EXPIRATION = 1000*60*60;

    public String extractUsername(String token){
        return  extractClaim(token, Claims::getSubject);
    }

    public String generateToken(String username){
        return generateToken(new HashMap<>() , username);
    }



    public String generateToken(
            Map<String , Object> extraClaims,
            String username){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();
    }


    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpire(token) );
    }


    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }


    // extract one claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyByte);   //HMAC (Hash-based Message Authentication Code)
    }
}
