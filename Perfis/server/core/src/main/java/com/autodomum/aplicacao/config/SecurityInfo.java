package com.autodomum.aplicacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author sabrina on 26/05/16.
 */
@Component
public class SecurityInfo {

    @Value("${security.autodomum.signatura-key}")
    private String signatureKey;

    @Value("${security.autodomum.issuer.name}")
    private String issuer;

    @Value("${security.autodomum.token-expiration-time.minutes}")
    private Long tokenExpirationInMinutes;

    public String getSignatureKey() {
        return signatureKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public Duration getTokenExpirationInMinutes() {
        return Duration.ofMinutes(tokenExpirationInMinutes);
    }
}
