package com.imyeego.frame.concurrent_modification_exception;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.imyeego.frame.bean.Student;
import com.imyeego.frame.generics.ICallback;
import com.imyeego.kotlin.Bean;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class ConcurrentTest {
    private static volatile int i = 0;
    public static void main(String[] args) {
//        removeByForeach();
//        removeByIterator();
        String day = "2019-10-19 9:00";
        Date oldDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        try {
            oldDate = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String s = sdf.format(new Date());
        System.out.println(new Date().after(oldDate));
//        String time = "5:00-7:00";
//        String[] times = time.split("-");
//        for (String s : times) {
//            System.out.println(s);
//        }
//
//        System.out.println(time);



//        String date = format("10月10日");
//        System.out.println(date);

//        String s = "liuzhao";
//        System.out.println(s.substring(5, 7));
//        System.out.println(Calendar.getInstance(Locale.CHINA).getTimeInMillis());



//        testSocket();
//        testGson();
//        int a = 100;
//        System.out.println(a >> 1);
//        System.out.println(a);
//        testGenerics();
//        testSwitch();

    }


    private static void testThread() {
        Thread.yield();
    }

    private static void testGenerics() {
        ICallback<Student> list = new ICallback<Student>() {
            @Override
            public void onSuccess(Student student) {

            }
        };
        Type[] types = list.getClass().getGenericInterfaces();
        Type type = ((ParameterizedType)types[0]).getActualTypeArguments()[0];
        Class<?> clazz = (Class<?>) type;
        Set<Class<?>> set = new HashSet<>();
        set.add(clazz);
        System.out.println();
    }

    private static void testGson() {
//        String response = "[{\"result\":\"success\"}]";
        String response = "[{\"id\":0,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\",\"classTh\":null}]";
        Student student = new Student();
        student.setId(0L);
        student.setName("Jess");
        student.setGender(1);
        student.setGrade("3");
//        student.setClassTh("2");
//        student.setIsUpload("");
        GsonBuilder builder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls();
//        builder.serializeNulls();
//        String json = builder.create().toJson(student);
//        JsonParser jsonParser = new JsonParser();
//        JsonArray jsonElements = jsonParser.parse(response).getAsJsonArray();//获取JsonArray对象
//        for (JsonElement element : jsonElements) {
//            Student bean = builder.create().fromJson(element, Student.class);
//            System.out.println(bean.getName());
//        }
//        System.out.println(json);
        List<Student> list = builder.create().fromJson(response, new TypeToken<List<Student>>(){}.getType());
//        List<Student> list = null;
//        for (Student s : list) {
//            System.out.println(s.getName());
//        }
        try {
            Class<?> clazz = Class.forName("java.lang.Integer");
            System.out.println(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testSocket() {
        String str = "[{\"SN\":\"54:E1:AD:F1:8E:D5\",\"aim\":\"download_sfrz\",\"version\":\"0.1\"}]";
        String str2 = "[{\"SN\":\"54:E1:AD:F1:8E:D5\",\"aim\":\"upload_sfrz_result\",\"version\":\"0.1\"},{\"devSN\":\"75862851220127035165\",\"zwtp\":\"\",\"rlppl\":\"0.0\",\"rlzp\":\"\",\"rzfs\":\"123\",\"rzjg\":\"2\",\"rzsj\":\"2019-06-26 09:57:15\",\"sfzzp\":\"\",\"zjhm\":\"410523198611280035\",\"zwppl\":\"0.0\"}]\n";
        String str1 = "[{\"SN\":\"54:E1:AD:F1:8E:D5\",\"aim\":\"upload_sfrz_result\",\"version\":\"0.1\"},{\"zjhm\":\"610124198810271029\",\"rzsj\":\"2019-07-08 08:25:25\",\"rzfs\":\"12\",\"rzjg\":\"1\",\"devSN\":\"\",\"zwppl\":\"\", \"rlppl\":\"0.36\",\"rlzp\":\"\",\"zwtp\":\"\",\"sfzzp\":\"\"}]";
        Socket client = null;
        try {
            client = new Socket();
            SocketAddress address = new InetSocketAddress("222.91.163.154", 12789);
//            SocketAddress address = new InetSocketAddress("192.168.10.101", 8888);
            client.connect(address, 3000);
            client.setSoTimeout(5_000);
            //构建IO
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write(str2 + "\r\n");
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println("服务器："+mess);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    System.out.println("连接已关闭");
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void testSwitch() {
        String i = "liuzhao";
        switch (i) {
            case "liuzhao":
                System.out.println(0);
            case "Jay Chou":
                System.out.println(1);
                break;
            default:
                System.out.println("您的输入是 " + i);
        }
    }

    private static Runnable task = () -> {
        try {
            Thread.sleep(5000);
            System.out.println(++i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static void removeByForeach(@NotNull String s){
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 5, 8));
        for (Integer integer : list){
            if (integer.intValue() == 2){
                list.remove(integer);
            }
        }
    }

    private static void removeByIterator(){
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 5, 8));
        //                list.remove(integer); //incorrect!this can throw ConcurrentModificationException
        list.removeIf(integer -> integer == 2);
        list.forEach(System.out::println);

    }

    private static String format(String date) {
        char[] dateArray = date.toCharArray();
        if (date.charAt(0) == '0' && date.charAt(3) == '0') {
            char[] chars = new char[4];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 0 || i == 3) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }
        if (date.charAt(0) == '0' && date.charAt(3) != '0') {
            char[] chars = new char[5];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 0) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }

        if (date.charAt(0) != '0' && date.charAt(3) == '0') {
            char[] chars = new char[5];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 3) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }

        return date;
    }
}
