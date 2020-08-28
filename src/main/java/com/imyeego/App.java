package com.imyeego;

import com.google.gson.Gson;
import com.imyeego.frame.generics.ICallback;
import com.imyeego.frame.generics.Movie;
import com.imyeego.frame.generics.Parse;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {

    private static List<String> list;
    public static String JSON = "{\n" +
            "    \"code\":200,\n" +
            "    \"status\":\"OK\",\n" +
            "    \"data\":{\n" +
            "        \"title\":\"社交网络\",\n" +
            "        \"rank\":26,\n" +
            "        \"time\":\"2014\",\n" +
            "        \"score\":9.0\n" +
            "    }\n" +
            "}";

    private static Callback callback;

    public static void main( String[] args ) {
//        list = new LinkedList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        listToSet(list);
//        parse();
//        System.out.println("liuzao".length());
//        List<Entity> list = new ArrayList<>();
//        Entity entity1 = new Entity(0, "liuzhao", "rt", false);
//        list.add(entity1);
//
//        for (int i = 1; i < 5; i++) {
//            Entity entity = new Entity(0, "liuzhao", "rt", false);
//            entity.id = i;
//
//            list.add(entity);
//        }
//
//        System.out.println(new Gson().toJson(list));
        testRegx();
    }


    public static void parse(){
        Parse.parse(JSON, new ICallback<Movie>() {
            @Override
            public void onSuccess(Movie movie) {
                System.out.println(movie.toString());
            }
        });
    }

    private static <T> void listToSet(List<T> list){
        Set<T> set = new HashSet<>();
        set.addAll(list);

        set.iterator().forEachRemaining(t -> System.out.println(t));
    }

    public static class Callback implements ICallback<Movie>{
        @Override
        public void onSuccess(Movie movie) {
            System.out.println(movie.toString());
        }
    }

    private static void testRegx() {
        String pattern = "JYD\\-AT9800\\-(D|E|HC|LC|HT).{2}";
        System.out.println(Pattern.matches(pattern, "JYD-AT9800-HC23"));
        System.out.println(Pattern.matches(pattern, "JYD-AT9800-HT23"));
        System.out.println(Pattern.matches(pattern, "JYD-AT9800-E"));
        System.out.println(Pattern.matches(pattern, "JYD-AT9800-EXX"));
    }


    static class Entity {
        int id;
        String name;
        String clazz;
        boolean click;

        public Entity(int id, String name, String clazz, boolean click) {
            this.id = id;
            this.name = name;
            this.clazz = clazz;
            this.click = click;
        }
    }
}
