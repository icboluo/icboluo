package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-22 22:20
 */
class N0917_仅反转字母 {
    public String reverseOnlyLetters(String s) {
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        int left = 0;
        while (left < right) {
            if (Character.isLetter(chars[left])) {
                while (!Character.isLetter(chars[right])) {
                    right--;
                }
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                // 右指针需要移动
                right--;
            }
            left++;
        }
        String res = "";
        for (char aChar : chars) {
            res += aChar;
        }
        return res;
    }
}
