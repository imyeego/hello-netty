package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 4/12/21:5:40 PM
 * @des :
 */
public class Code69 {

    public static void main(String[] args) {

    }

    static int sqrt(int x) {
        if (x == 0 || x == 1) return x;
        int l = 1, r = x, res = 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (mid == x / mid) return mid;
            else if (mid > x / mid) r = mid - 1;
            else {
                l = mid + 1;
                res = mid;
            }
        }


        return res;
    }
}
