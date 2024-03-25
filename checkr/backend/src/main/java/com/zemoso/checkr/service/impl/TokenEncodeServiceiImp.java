package com.zemoso.checkr.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenEncodeServiceiImp {
    private final JwtEncoder encoder;

    public TokenEncodeServiceiImp(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication){
        var encodeParameters = getJwtEncoderParameters(authentication);
        return this.encoder.encode(encodeParameters).getTokenValue();
    }

    private static JwtEncoderParameters getJwtEncoderParameters(Authentication authentication) {
        Instant now= Instant.now();
        String scope= authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority->!authority.startsWith("ROLE"))
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims=JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();
        return JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),claims);
    }

//    public boolean validateToken(String token) {
//        return !isTokenExpired(token);
//    }


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
