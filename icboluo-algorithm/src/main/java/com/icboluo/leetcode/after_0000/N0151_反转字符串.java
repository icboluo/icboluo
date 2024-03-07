package com.icboluo.leetcode.after_0000;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-12-26 8:32
 */
class N0151_反转字符串 {
    public static void main(String[] args) {
        var cla = new N0151_反转字符串();
        System.out.println(cla.reverseWords("ab cd"));
    }

    public String reverseWords(String s) {
        // 方法1：s.split reverse
        // 方法2：s.reverse word.reverse
        char[] arr = s.toCharArray();
        reverse(arr);
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            char ch = arr[right];
            if (ch == ' ') {
                reverse(arr, left, right - 1);
                left = right + 1;
            }
        }
        reverse(arr, left, arr.length - 1);
        String res = "";
        for (char c : arr) {
            res += c;
        }
        return Arrays.stream(res.split(" ")).filter(str -> !str.isBlank()).collect(Collectors.joining(" "));
    }

    public void reverse(char[] arr) {
        int left = 0;
        int right = arr.length - 1;
        reverse(arr, left, right);
    }

    public void reverse(char[] arr, int left, int right) {
        char temp;
        while (left < right) {
            temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}
