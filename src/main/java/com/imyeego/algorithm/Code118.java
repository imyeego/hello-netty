package com.imyeego.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @authur : liuzhao
 * @date : 3/6/21:2:32 PM
 * @des :
 */
public class Code118 {

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        for (List<Integer> row: result) {
            for (Integer value : row) {
                System.out.print("" + value + ", ");
            }
            System.out.println(".");
        }
    }

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> l1 = Arrays.asList(1);
        list.add(l1);
        if (numRows == 1) return list;
        int p = 0, q = 1; //上一行待相加的数的位置
        List<Integer> last = l1; //上一行list
        for (int i = 2; i <= numRows; i++) {
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    l.add(1);
                    continue;
                }
                int value = last.get(p) + last.get(q);
                l.add(value);
                p++;
                q++;
            }


            list.add(l);
            last = l;
            p = 0;
            q = 1;
        }





        return list;
    }
}
