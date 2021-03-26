package com.imyeego.algorithm;

/**
 * @authur : liuzhao
 * @date : 3/26/21:9:45 PM
 * @des :
 */
public class Offer4 {

    public static void main(String[] args) {
        String str = "I am a student";

        System.out.println(replaceBlank(str));
    }

    static String replaceBlank(String str) {
        if (str == null || !str.contains(" ")) return str;

        char[] chars = str.toCharArray();
        int length = str.length();
        int count = 0;

        for (char c : chars) {
            if (c == ' ') count ++;
        }


        char[] newStr = new char[length + 2 * count];
        System.arraycopy(chars, 0, newStr, 0, length);
        int j = newStr.length - 1;
        for (int i = length - 1; i >= 0 && j >= 0; i --) {
            if (newStr[i] != ' ') newStr[j--] = newStr[i];
            else {
                newStr[j--] = '0';
                newStr[j--] = '2';
                newStr[j--] = '%';
            }
        }

        return new String(newStr);
    }
}
