package com.SupermarketPOS.Backend.Config.security;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
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
    private long expiration = 1000*60*60*24;
    private final EmployeeRepository employeeRepository;
    private final UserInfoUserDetailsService userInfoUserDetailsService;

    public JwtService(EmployeeRepository employeeRepository, UserInfoUserDetailsService userInfoUserDetailsService) {
        this.employeeRepository = employeeRepository;
        this.userInfoUserDetailsService = userInfoUserDetailsService;
    }


    public String generateToken(String username){
        UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(username);
        Employee user = employeeRepository.findByEmail(username).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getJobRole().toString());
        claims.put("branch", (user.getBranch() != null) ? user.getBranch().getId() : null);
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
