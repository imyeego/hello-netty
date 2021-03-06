package com.imyeego.algorithm;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/3 0003 22:46
 * @Email: imyeego@gmail.com
 * @Description: 字符串转数字 str to int
 */
public class Offer49 {

    public static void main(String[] args) {

        String s = "  -0012a42";
        System.out.println(str2int(s));
    }


    /**
     * LeetCode#8
     * @param str
     * @return
     */
    private static int str2int(String str){
        // clear blank space
        String s = str.trim();
        if (s.isEmpty()) return 0;

        long result = 0, flag = 0;
        if (s.charAt(0) == '-'){
            flag = 1;
        }
        if (s.charAt(0) == '-' || s.charAt(0) == '+'){
            s = s.substring(1);
        }

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i ++){
            char c = chars[i];
            if (!(c >= '0' && c <= '9')) {
                if (i == 0) return 0;
                if (flag == 1) result = -result;
                break;
            }
            result = 10 * result + c - '0';
            if (result <= Integer.MAX_VALUE && flag == 1 && i == chars.length - 1){
                return (int) (-result);
            }
            if (result > Integer.MAX_VALUE){
                if (flag == 1) {
                    return -Integer.MAX_VALUE - 1;
                }
                result = Integer.MAX_VALUE;
            }

        }
        return (int) result;
    }
}
