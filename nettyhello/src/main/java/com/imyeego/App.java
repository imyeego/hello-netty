package com.imyeego;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
//        AtomicInteger i = new AtomicInteger(3);
//        new Thread(() -> {
//            i.getAndIncrement();
//            System.out.println(i.get());
//        }).start();

        isEquals();
    }



    private static void isEquals(){
        String s1 = "liuzhao";
        String s2 = "liuzhao";

        Student stu1 = new Student("liuzhao");
        Student stu2 = new Student("liuzhao");

        System.out.println(s1 == s2);
        System.out.println(stu1 == stu2);
    }


    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }
    }
}
