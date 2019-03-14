package com.imyeego.algorithm;

import java.util.Stack;

/**
 * @authur : liuzhao
 * @time : 2019/3/14
 * @email : imyeego@gmail.com
 * @description : the quick-sort with recursive and iterator
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {3, 6, 1, 12, 7, 4, 67, 3, 15, 32, 9};
        quickSortIteratorly(array, 0, array.length - 1);
//        swap(array, 1, 4);
        for (int num : array) {
            System.out.print(num + ", ");
        }
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int i = left, j = right, t;
        int temp = array[left];
        while (i < j) {
            while (i < j && array[j] >= temp) j --;
            while (i < j && array[i] <= temp) i ++;
            if (i != j) {
                t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        array[left] = array[i];
        array[i] = temp;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    private static void quickSort1(int[] array, int left, int right) {
        if (left >= right) return;
        int i = partition(array, left, right);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    private static void quickSortIteratorly(int[] array, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        stack.push(left);
        stack.push(right);

        while (!stack.empty()) {
            int r = stack.pop();
            int l = stack.pop();
            int i = partition(array, l, r);
            if (i - 1 > l) {
                stack.push(left);
                stack.push(i - 1);
            }
            if (i + 1 < r) {
                stack.push(i + 1);
                stack.push(right);
            }
        }
    }

    private static int partition(int[] array, int left, int right) {
        int i = left, j = right;
        int temp = array[left];

        while (i < j) {
            while (i < j && array[j] >= temp) j --;
            array[i] = array[j];
            while (i < j && array[i] <= temp) i ++;
            array[j] = array[i];
        }
        array[i] = temp;
        return i;
    }

}
