package com.imyeego.json;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        String json = "{\"id\":422,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"}";
        String json = "{\"code\":200,\"msg\":\"success\",\"list\":[{\"id\":422,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"}]}";
        Logger.d(json);
        JsonParser jsonParser = new JsonParser();
//        Student student = jsonParser.fromJson(json, Student.class);
//        Logger.d(student.toString());
        Response response = jsonParser.fromJson(json, Response.class);
//        Logger.d(response.toString());

        String jsonList = "[{\"id\":422,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"},{\"id\":34,\"name\":\"Liu zhao\",\"gender\":1,\"grade\":\"2\"}]";
        List<Student> list = jsonParser.fromJsonList(jsonList, Student.class);
//        for (Student s : list) {
//            Logger.d(s.toString());
//        }
//        Logger.d(new JsonParser().toJson(response));

//        Class<Student> clazz = Student.class;
//        try {
//            Field field = clazz.getDeclaredField("id");
//            field.setAccessible(true);
//            Object o = field.get(student);
//            field.setAccessible(false);
//            System.out.println(o.getClass().getName());
//            System.out.println(o.getClass().getTypeName());
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
        System.out.println(jsonParser.toJson(list, Student.class));
//        Type type = list.getClass().getTypeParameters();
//        String eleType = ((ParameterizedType)type).getActualTypeArguments()[0].getTypeName();
//        System.out.println(list.getClass().getTypeName());
//        Class<Response> clazz = Response.class;
//        try {
//            Field field = clazz.getDeclaredField("list");
//            Type type = field.getGenericType();
//            System.out.println(field.getType().getName());
//            System.out.println(((ParameterizedType)type).getRawType().getTypeName());
//            String eleType = ((ParameterizedType)type).getActualTypeArguments()[0].getTypeName();
//            Class<?> clz = Class.forName(eleType);
//            System.out.println(clz.getName());
//        } catch (ClassNotFoundException | NoSuchFieldException e) {
//            e.printStackTrace();
//        }


    }

    static class Student {
        private long id;
        private String name;
        private int gender;
        private String grade;

        public long getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", gender=" + gender +
                    ", grade='" + grade + '\'' +
                    '}';
        }
    }

    static class Response {
        int code;
        String msg;
        List<Student> list;

        @Override
        public String toString() {
            return "Response{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", list=" + list +
                    '}';
        }
    }
}
