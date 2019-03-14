package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @time : 2019/3/14
 * @email : imyeego@gmail.com
 * @description :
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {3, 6, 1, 12, 7, 4, 67, 3, 15, 32, 9};
        quickSort(array, 0, array.length - 1);
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

}
