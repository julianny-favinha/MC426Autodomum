package com.autodomum.service;

import com.autodomum.aplicacao.config.SecurityInfo;
import io.jsonwebtoken.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author sabrina on 26/05/16.
 */
public class AuthenticationService {

    public static final String AUTH_COOKIE = "x-auth-token";

    private final SecurityInfo securityInfo;

    public AuthenticationService(SecurityInfo securityInfo) {
        this.securityInfo = securityInfo;
    }

    public String getAuthenticatedUserUsername(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(securityInfo.getSignatureKey()).parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public String generateToken(String userId) {
        Date expirationDate = Date.from(
                LocalDateTime.from(
                        securityInfo.getTokenExpirationInMinutes()
                                .addTo(LocalDateTime.now()))
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        return Jwts.builder().setIssuer(securityInfo.getIssuer())
                .setSubject(userId)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, securityInfo.getSignatureKey())
                .compact();
    }

    public boolean isValidToken(String token) {
        if(token == null || token.isEmpty()) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(securityInfo.getSignatureKey()).parseClaimsJws(token);
            //OK, we can trust this JWT
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }
}
