package com.icboluo.leetcode.after_0200;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-10-31 13:15
 */
public class N0242_有效的字母异位词 {
    public static void main(String[] args) {
        N0242_有效的字母异位词 cla = new N0242_有效的字母异位词();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean b = cla.m1(str1, str2);
        System.out.println("b = " + b);
    }

    /**
     * 排序 TODO 哈希
     *
     * @param str1
     * @param str2
     * @return
     */
    private boolean m1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str1.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
}
