package com.imyeego.functional;

import java.util.Optional;

public class FunctionalTest {

    public static void main(String[] args) {
        /*User user = null;
        user = new User("liuzhao");

        System.out.println(getName(user));*/

//        Inner inner = new Inner("Inner");
        Inner inner = null;
        Nested nested = new Nested(inner);
//        Nested nested = null;
        Outer outer = new Outer(nested);
        System.out.println(getMsg(outer));


    }

    private static String getName(User user){
        return Optional.ofNullable(user).map(User::getUserName).orElse("unKnown");
    }

    private static String getMsg(Outer outer){
        return Optional.ofNullable(outer)
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getMsg)
                .orElse("unKnown");
    }
}
