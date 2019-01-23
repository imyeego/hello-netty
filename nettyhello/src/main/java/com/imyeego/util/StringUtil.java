package com.imyeego.util;

public class StringUtil {

    public static String lowerFirst(String dest) {
        if (dest == null  || dest.isEmpty()) {
            return dest;
        }
        char c[] = dest.toCharArray();
        c[0] += 32;
        return new String(c);
    }
}
