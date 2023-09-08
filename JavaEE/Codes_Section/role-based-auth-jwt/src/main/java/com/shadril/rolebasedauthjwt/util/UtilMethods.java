package com.shadril.rolebasedauthjwt.util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;

@UtilityClass
public class UtilMethods {

    public static String generateRandomString(int size) {
        SecureRandom secureRandom = new SecureRandom();
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int index = secureRandom.nextInt(validChars.length());
            sb.append(validChars.charAt(index));
        }
        return sb.toString();
    }


    public static Timestamp calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
