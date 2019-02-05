package com.imyeego.algorithm;

/**
 * @Authur: liuzhao
 * @Date: 2019/2/3 0003 22:46
 * @Email: imyeego@gmail.com
 * @Description: 字符串转数字 str to int
 */
public class Offer49 {

    public static void main(String[] args) {

        String s = "2355576";
        System.out.println(str2int(s));
    }


    /**
     *
     * @param string
     * @return
     */
    private static int str2int(String string){
        // clear blank space
        String s = string.trim().replace(" ", "");
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
                break;
            }
            result = 10 * result + c - '0';
            if (result == Integer.MAX_VALUE && flag == 1 && i == chars.length - 1){
                return (int) (result - 2 * result);
            }
            if (result > Integer.MAX_VALUE){
                result = Integer.MAX_VALUE;
                break;
            }

        }

        if (flag == 1) {
            return (int) (result == Integer.MAX_VALUE ? result - 2 * result - 1 : result - 2 * result);
        }
        return (int) result;
    }
}
