package com.digitopia.digitopiacasestudy.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MD5 {

    public static String getMd5(String userMail) {

        final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        final String hash = userMail.concat("_").concat(date);

        try {
            MessageDigest messageDigestInstance = MessageDigest.getInstance("MD5");

            byte[] messageDigest = messageDigestInstance.digest(hash.getBytes());

            StringBuilder createdString = new StringBuilder();
            for (byte message: messageDigest) {
                String hex = Integer.toHexString(0xff & message);
                if (hex.length() == 1) createdString.append('0');
                createdString.append(hex);
            }
            return createdString.toString();

        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
