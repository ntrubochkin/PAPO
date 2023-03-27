package com.noname.books_exchange.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureStringProvider {
    private static SecureRandom random;
    
    static {
        try {
            random = SecureRandom.getInstance("NativePRNG");
        } catch(NoSuchAlgorithmException nsae) {
            random = new SecureRandom();
        }
    }

    public static String getPassword() {
        String result = "";
        do {
            result = getNextRandomString(12);
        } while(!RegexUtils.validatePassword(result));
        return result;
    }

    public static String getVerificationKey() {
        return getNextRandomString(128);
    }

    //https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string 
    private static String getNextRandomString(int length) {
        int bits = length * 5; //1 base32 char = 5 bits
        BigInteger number = new BigInteger(bits, random);
        return number.toString(32);
    }
}