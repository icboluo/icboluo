package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-07-06 19:53
 */
class N0344_反转数组 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }
}
