package com.imyeego.json;

import java.lang.reflect.Field;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = "{\"id\":422,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"}";

        Logger.d(json);
        JsonParser jsonParser = new JsonParser();
        Student student = jsonParser.fromJson(json, Student.class);
//        Student student = jsonParser.fromJson(json, Student.class);
//        Logger.d(student.toString());

//        String jsonList = "[{\"id\":422,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"},{\"id\":34,\"name\":\"Liu zhao\",\"gender\":1,\"grade\":\"2\"}]";
//        List<Student> list = jsonParser.fromJsonList(jsonList, Student.class);
//        for (Student s : list) {
//            Logger.d(s.toString());
//        }
        Logger.d(new JsonParser().toJson(student));

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
}
