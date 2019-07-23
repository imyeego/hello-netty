package com.imyeego.json;

public class Logger {

    private final static String TAG = "JSON";

    public static void d(String message) {
        System.out.println(TAG + " : " + message);
    }

    public static void d(int i) {
        Logger.d("" + i);
    }
}
