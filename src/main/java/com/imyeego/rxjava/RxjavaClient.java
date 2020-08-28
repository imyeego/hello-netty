package com.imyeego.rxjava;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class RxjavaClient {

    private static int LENGTH = 20;
    private static ExecutorService service = Executors.newCachedThreadPool();

    //密钥
    private static String pwd = "CSJJNYTQV186EPZA";
    private static String alg = "AES/CBC/pkcs5padding";
    //偏移量
    private static String offset = "OM2NRXJABEFX3LXA";

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
//        schedulers();
//        List<String> list = Arrays.asList("五 文综", "二 数学", "一 语文", "四 理综","三 英语", "十二次","56");
//        Collections.sort(list, (Comparator.comparingInt(RxjavaClient::chineseNum2Int)));
//        list.forEach(System.out::println);

//        String s = "第十一场次";
//        System.out.println(chineseNum2Int(s));
//        try {
//            System.out.println(aesEncode("jingyeda12"));
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        testJ();

    }

    private static void testJ() {
        String j = "[\n" +
                "        [\n" +
                "            \"1\",\n" +
                "            \"汉族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"2\",\n" +
                "            \"蒙古族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"3\",\n" +
                "            \"回族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"4\",\n" +
                "            \"藏族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"5\",\n" +
                "            \"维吾尔族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"6\",\n" +
                "            \"苗族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"7\",\n" +
                "            \"彝族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"8\",\n" +
                "            \"壮族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"9\",\n" +
                "            \"布依族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"10\",\n" +
                "            \"朝鲜族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"11\",\n" +
                "            \"满族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"12\",\n" +
                "            \"侗族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"13\",\n" +
                "            \"瑶族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"14\",\n" +
                "            \"白族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"15\",\n" +
                "            \"土家族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"16\",\n" +
                "            \"哈尼族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"17\",\n" +
                "            \"哈萨克族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"18\",\n" +
                "            \"傣族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"19\",\n" +
                "            \"黎族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"20\",\n" +
                "            \"傈僳族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"21\",\n" +
                "            \"佤族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"22\",\n" +
                "            \"畲族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"23\",\n" +
                "            \"高山族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"24\",\n" +
                "            \"拉祜族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"25\",\n" +
                "            \"水族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"26\",\n" +
                "            \"东乡族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"27\",\n" +
                "            \"纳西族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"28\",\n" +
                "            \"景颇族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"29\",\n" +
                "            \"柯尔克孜族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"30\",\n" +
                "            \"土族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"31\",\n" +
                "            \"达斡尔族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"32\",\n" +
                "            \"仫佬族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"33\",\n" +
                "            \"羌族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"34\",\n" +
                "            \"布朗族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"35\",\n" +
                "            \"撒拉族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"36\",\n" +
                "            \"毛南族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"37\",\n" +
                "            \"仡佬族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"38\",\n" +
                "            \"锡伯族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"39\",\n" +
                "            \"阿昌族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"40\",\n" +
                "            \"普米族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"41\",\n" +
                "            \"塔吉克族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"42\",\n" +
                "            \"怒族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"43\",\n" +
                "            \"乌孜别克族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"44\",\n" +
                "            \"俄罗斯族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"45\",\n" +
                "            \"鄂温克族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"46\",\n" +
                "            \"德昂族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"47\",\n" +
                "            \"保安族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"48\",\n" +
                "            \"裕固族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"49\",\n" +
                "            \"京族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"50\",\n" +
                "            \"塔塔尔族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"51\",\n" +
                "            \"独龙族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"52\",\n" +
                "            \"鄂伦春族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"53\",\n" +
                "            \"赫哲族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"54\",\n" +
                "            \"门巴族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"55\",\n" +
                "            \"珞巴族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"56\",\n" +
                "            \"基诺族\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"59\",\n" +
                "            \"穿青人\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"95\",\n" +
                "            \"摩梭人\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"97\",\n" +
                "            \"其他\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"98\",\n" +
                "            \"外国血统中国籍人士\"\n" +
                "        ]\n" +
                "    ]";

        List<List<String>> lists = new Gson().fromJson(j, new TypeToken<List<List<String>>>(){}.getType());
        for (List<String> list : lists) {
            System.out.println(list.get(1) + " ," + (list.get(0).length() == 1 ? "0" + list.get(0):list.get(0)));
        }
    }

    public static String aesEncode(String content)
            throws GeneralSecurityException,
            IOException {
        SecretKey key = new SecretKeySpec(pwd.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(alg);
        IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] byte_encode = content.getBytes(StandardCharsets.UTF_8);
        byte[] byte_AES = cipher.doFinal(byte_encode);
        return parseByte2HexStr(byte_AES);
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
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
                left = mirror ? oldRect.width - oldRect.right : oldRect.left;
                top = oldRect.top;
                right = mirror ? oldRect.width - oldRect.left : oldRect.right;
                bottom = oldRect.bottom;
                break;
            case 90:
                left = mirror ? oldRect.top : oldRect.height - oldRect.bottom;
                top = oldRect.left;
                right = mirror ? oldRect.bottom : oldRect.height - oldRect.top;
                bottom = oldRect.right;
                break;
            case 180:
                left = !mirror ? oldRect.width - oldRect.right : oldRect.left;
                top = oldRect.height - oldRect.bottom;
                right = !mirror ? oldRect.width - oldRect.left : oldRect.right;
                bottom = oldRect.height - oldRect.top;
                break;
            case 270:
                left = !mirror ? oldRect.top : oldRect.height - oldRect.bottom;
                top = oldRect.width - oldRect.right;
                right = !mirror ? oldRect.bottom : oldRect.height - oldRect.top;
                bottom = oldRect.width - oldRect.left;
                break;
        }
        return new Rect(left, top, right, bottom);
    }


    static class Rect {
        int left, top, right, bottom;
        int width, height;

        public Rect(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
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
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c.equals(d));
    }

}
