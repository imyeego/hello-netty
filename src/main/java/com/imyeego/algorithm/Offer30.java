package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/8 0008 23:56
 * @Email: imyeego@gmail.com
 * @Description: least k numbers in no-sorted list
 */
public class Offer30 {

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 6, 2, 7, 3, 8};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        leastKNumbersUsingHeap(list, 3).stream().forEach(System.out::println);
    }

    private static List<Integer> leastKNumbersUsingHeap(List<Integer> arrays, int k){
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(arrays);
        for (int i = 0; i < k; i++) {
            list.add(queue.poll());
        }

        return list;

    }
}
