package com.example.securityJWT.auth.utils;

import com.example.securityJWT.auth.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String jwtsecret;
    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;


    public String getUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

//    public List<String> getRoles(String token){
//        Object object = extractClaim(token, Claims::getSubject);
//        return
//    }

    public List<?> getRoles(String token){
        return extractAllClaims(token).get("roles", List.class);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        if(userDetails instanceof User customUserDetails){
            claims.put("id", customUserDetails.getId());
            claims.put("roles", customUserDetails.getRoles());
            claims.put("email", customUserDetails.getEmail());
        }
        return buildToken(claims, userDetails);
    }

    private String buildToken(Map<String,Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtLifetime.toMillis()))
                .signWith(getSigningKey())
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtsecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
