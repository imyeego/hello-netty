package com.imyeego.frame.concurrent_modification_exception;

class StaticTest {
        public static boolean is = false;
        public static int i = 5;
        
        static {
            if (is) i = 5;
            else i = 3;
            System.out.println(i);
        }
}