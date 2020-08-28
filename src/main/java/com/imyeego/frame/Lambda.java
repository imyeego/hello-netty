package com.imyeego.frame;

import java.util.*;
import java.util.function.Supplier;

public class Lambda {

    public static int s = 1;
    public static final String ss = "np";

    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
        System.out.println(ss);
//        System.out.println(s);
//        Strategy card = new VerifyWithCard();
//        card.skip();
//        card.next();
//
//        Strategy db = new VerifyWithDb();
//        db.skip();
//        db.next();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("延时任务");
//                timer.cancel();
//            }
//        }, 5000);
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("11");
//        list.add("22");
//        list.add("02");
//        list.add("8");
//        list.add("07");
//        list.add("3");
//        list.add("256");
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return Integer.parseInt(o1) - Integer.parseInt(o2);
//            }
//        });
//        list.stream().forEach(System.out::println);
    }



    private static void lambda(){
        final int[] answer = {0};
        new Thread(() -> {
            answer[0]++;
            System.out.println(answer[0]);
        }).start();
    }

    private static void lambda1(){
        int myVar = 42;
        Supplier<Integer> lambdaFun = () -> myVar; // error
//        myVar++;
//        System.out.println(lambdaFun.get());
    }
}
