package com.imyeego.frame.concurrent_modification_exception;

public class Login {

    public static Login loginBean;

    public static Login instance() {
        if (loginBean == null) {
            loginBean = new Login();
        }


        return loginBean;
    }
}
