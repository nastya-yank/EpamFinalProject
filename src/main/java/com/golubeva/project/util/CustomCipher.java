package com.golubeva.project.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The {@code CustomCipher} class represents custom cipher.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CustomCipher {
    private static final String CIPHER_FORMAT = "%02x";

    /**
     * Encrypt string.
     *
     * @param string the string
     * @return the encrypted string
     */
    public static String encrypt(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] data = messageDigest.digest(string.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(String.format(CIPHER_FORMAT, data[i]));
        }
        return stringBuilder.toString();
    }
}
