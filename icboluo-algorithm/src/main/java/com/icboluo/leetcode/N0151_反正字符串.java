package com.icboluo.leetcode;

/**
 * @author icboluo
 * @since 2023-12-26 8:32
 */
class N0151_反正字符串 {
    // ab cd -> cd ab
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
        return res;
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
