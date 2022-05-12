package com.codecool.shop.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Password {
    byte[] salt = {-77, -30, 116, -70, -127, 36, 69, -116, 31, 90, -52, -128, -21, 3, -127, 123};

    public String generateHash(String password) throws InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert factory != null;
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Arrays.toString(hash);
    }

    public boolean isValid(String password, String hash) throws InvalidKeySpecException {
        return generateHash(password).equals(hash);
    }
}
