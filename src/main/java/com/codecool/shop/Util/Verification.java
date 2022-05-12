package com.codecool.shop.Util;

public class Verification {

    public static String passwordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verification(String original, String hashed) {
        return BCrypt.checkpw(original, hashed);
    }
}
