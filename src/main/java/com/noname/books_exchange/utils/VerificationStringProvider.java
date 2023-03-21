package com.noname.books_exchange.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class VerificationStringProvider {
    private static SecureRandom random;
    
    static {
        try {
            random = SecureRandom.getInstance("NativePRNG");
        } catch(NoSuchAlgorithmException nsae) {
            random = new SecureRandom();
        }
    }

    public static String getNextRandomString() {
        //https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
        //Base64: 1 char (b64) = 6 chars (b2) => 128 * 5 = 640
        BigInteger number = new BigInteger(640, random);
        return number.toString(32);
    }
}