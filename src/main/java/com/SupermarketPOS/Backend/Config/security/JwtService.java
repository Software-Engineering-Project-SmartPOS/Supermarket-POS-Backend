package com.SupermarketPOS.Backend.Config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private String SECRET = "RyBaEtTM/eBPnZS2wQDFBe+ErrbnE5Ur8MylzQN8WdMvylcQFCf2KO/LMZccJkGr";
    private long expiration = 1000*60*60;


    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims , username);
    }


    // creating a token
    public String createToken(Map<String, Object> claims, String username ){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expiration) )
                .signWith(getSignKey() , SignatureAlgorithm.HS256)
                .compact();
    }


    // to extract all the claims from the token
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(token)
                .getBody();
    }

//    function for separate out the different type of claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    extract the username for the token
    public String extractUsername(String token){
        return  extractClaim(token, Claims::getSubject);
    }

//    check the  validity of the token
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





    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
