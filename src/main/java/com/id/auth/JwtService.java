/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
//import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;

/**
 *
 * @author id
 */
@Service
class JwtService {
    
    private static final String SECRET_KEY = "XQ3FVTcksd8QMQLHvfe3eSlRu5KSpEsm1P8LNYUrUn8=";

    public String getToken(UserDetails userDetails) {
        return getToken(new HashMap<>(), userDetails);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    
    private boolean  isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
