package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-22 22:20
 */
class N0917_仅反转字母 {
    // TODO ERROR
    public String reverseOnlyLetters(String s) {
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (Character.isLetter(chars[i])) {
                while (!Character.isLetter(chars[right])) {
                    right--;
                }
                char temp = chars[i];
                chars[i] = chars[right];
                chars[right] = temp;
            }
        }
        String res = "";
        for (char aChar : chars) {
            res += aChar;
        }
        return res;
    }
}
