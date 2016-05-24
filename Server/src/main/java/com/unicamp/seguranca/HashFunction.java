package com.unicamp.seguranca;

import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

/**
 * @author sabrina on 19/05/16.
 */
public class HashFunction implements Function<String, String> {

    @Value("${security.meleva.hash}")
    private String algoritmoDeHash;

    public String apply(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algoritmoDeHash);
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(String.format("O algoritmo %s de hash nao existe", algoritmoDeHash), e);
        }
    }
}
