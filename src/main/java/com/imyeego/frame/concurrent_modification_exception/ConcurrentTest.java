package com.imyeego.frame.concurrent_modification_exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConcurrentTest
{
    public static void main(String[] args) {
//        removeByForeach();
        removeByIterator();
    }



    private static void removeByForeach(){
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
}
