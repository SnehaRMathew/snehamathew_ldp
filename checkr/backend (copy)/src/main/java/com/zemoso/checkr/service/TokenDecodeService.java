package com.zemoso.checkr.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenDecodeService {
    private final JwtDecoder decoder;

    public TokenDecodeService(JwtDecoder decoder){
        this.decoder = decoder;
    }

//    public boolean validateToken(String token) {
//        return !isTokenExpired(token);
//    }

    public String getUsernameFromToken(String token) {
        System.out.println("Get user details"+ decoder.decode(token).getClaims());
        return "Success";
    }

//    private boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//    }
//
//    private Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
}
