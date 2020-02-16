package com.imyeego.rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class RxjavaClient {

    private static int LENGTH = 20;
    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {
//        List<Integer> list = new LinkedList<>();
//
//        for (int i = 0; i < LENGTH; i++) {
//            Integer integer = (int)(Math.random() * 20 + 1);
//            list.add(integer);
//        }
//        print(list);
//        System.out.println("----------------------------------");
//        just(list);
//        equals();
        schedulers();
//        List<String> list = Arrays.asList("五 文综", "二 数学", "一 语文", "四 理综","三 英语", "十二次","56");
//        Collections.sort(list, (Comparator.comparingInt(RxjavaClient::chineseNum2Int)));
//        list.forEach(System.out::println);

//        String s = "第十一场次";
//        System.out.println(chineseNum2Int(s));
    }


    private static void just(List<Integer> list){
        Integer[] array = list.toArray(new Integer[list.size()]);
        Observable.from(array)
                .map(integer -> integer + 1)
                .doOnNext(System.out::println).subscribe();
    }

    private static void print(List<Integer> list){
        for (Integer integer : list){
            System.out.println(integer.intValue());
        }
    }

    private static void schedulers() {
//        Scheduler.Worker worker = Schedulers.computation().createWorker();
//        worker.schedule(() -> {
//
//        });
        Observable.just(createString())
                .map(s -> {
                    int i = Integer.parseInt(s);
                    return i;
                })
                .map(s -> {
                    System.out.println("mapped observable:" + Thread.currentThread().getName());
                    return s + 5;
                })
                .doOnNext(s -> {
                    System.out.println("observer:" + Thread.currentThread().getName());
                    System.out.println(s);
                }).subscribe();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String createString() {
        System.out.println("original observable:" + Thread.currentThread().getName());
        Future<String> furure = service.submit(() -> {
            Thread.sleep(5000);
            return "777";
        });
        String result = null;
        try {
            result = furure.get();
            if (furure.isDone()) service.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;

    }

    private static int chineseNum2Int(String s) {
        List<Character> c0 = Arrays.asList('零','一','二','三','四','五','六','七','八','九');
        List<Character> c1 = Arrays.asList('十','百','千','万');
        int result = 0;
        int multi = 0;
        boolean is = false;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int countOfNumber = 0;
        for (int i = length - 1; i >= 0 ; i--) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                result += (int) Math.pow(10, countOfNumber) * (c - 48);
                countOfNumber ++;
            } else {
                int i0 = c0.indexOf(c);
                int i1 = c1.indexOf(c);
                if (i0 == -1 && i1 == -1) {
                    if (countOfNumber != 0) break;
                } else {
                    if (i0 != -1) {
                        result += (int) Math.pow(10, multi) * i0;
                        multi ++;
                        is = false;
                    }
                    if (i1 != -1) {
                        multi = i1 + 1;
                        is = multi == 1;
                    }
                    countOfNumber ++;
                }
            }
        }

        return is ? result + 10  : result;
    }

    private static Rect newRect(Rect oldRect, boolean mirror, int rotation) {
        int top = 0, left = 0, bottom = 0, right = 0;
        switch (rotation % 360) {
            case 0:
                top = oldRect.top;
                left = mirror ? oldRect.width - oldRect.right : oldRect.left;
                right = mirror ? oldRect.width - oldRect.left : oldRect.right;
                bottom = oldRect.bottom;
                break;
            case 90:
                top = oldRect.left;
                left = mirror ? oldRect.top : oldRect.height - oldRect.bottom;
                right = mirror ? oldRect.bottom : oldRect.height - oldRect.top;
                bottom = oldRect.right;
                break;
            case 180:
                top = oldRect.height - oldRect.bottom;
                bottom = oldRect.height - oldRect.top;
                left = !mirror ? oldRect.width - oldRect.right : oldRect.left;
                right = !mirror ? oldRect.width - oldRect.left : oldRect.right;
                break;
            case 270:
                top = oldRect.width - oldRect.right;
                left = !mirror ? oldRect.top : oldRect.height - oldRect.bottom;
                right = !mirror ? oldRect.bottom : oldRect.height - oldRect.top;
                bottom = oldRect.width - oldRect.left;
                break;
        }
        return new Rect(left, top, right, bottom);
    }


    static class Rect {
        int top;
        int left;
        int right;
        int bottom;
        int width;
        int height;

        public Rect(int left, int top, int right, int bottom) {
            this.top = top;
            this.left = left;
            this.right = right;
            this.bottom = bottom;
        }
    }

    private static void equals() {
        String a = "ab";
        String b = "a" + "b";
        String c = new String("ab");
        String d = new String("ab");
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }

}
