package com.imyeego;

import com.imyeego.frame.generics.ICallback;
import com.imyeego.frame.generics.Movie;
import com.imyeego.frame.generics.Parse;

import java.util.*;

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
        list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
//        listToSet(list);
//        parse();

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
        for(T t : list){
            set.add(t);
        }

        set.iterator().forEachRemaining(t -> System.out.println(t));
    }

    public static class Callback implements ICallback<Movie>{
        @Override
        public void onSuccess(Movie movie) {
            System.out.println(movie.toString());
        }
    }



}
