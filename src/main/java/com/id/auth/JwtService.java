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
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author id
 */
@Service
class JwtService {
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String getToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
//    public String getToken(UserDetails userDetails) {
//        return getToken(new HashMap<>(), userDetails);
//    }
//
//    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                        .setSubject(userDetails.getUsername())
//                        .setIssuedAt(new Date(System.currentTimeMillis()))
//                        .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//    
//    private Key getKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    String getUsernameFromToken(String token) {
//        return getClaim(token, Claims::getSubject);
//    }
//
//    boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    
//    private Claims getAllClaims(String token){
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    
//    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
//        final Claims claims = getAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    
//    private Date getExpiration(String token){
//        return getClaim(token, Claims::getExpiration);
//    }
//    
//    private boolean  isTokenExpired(String token){
//        return getExpiration(token).before(new Date());
//    }
}
