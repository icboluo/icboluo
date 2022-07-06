package com.icboluo.leetcode.双指针;

/**
 * @author icboluo
 * @since 2022-07-06 19:57
 */
class N0005_最长回文子串 {
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String str1 = hwc(s, i, i);
            String str2 = hwc(s, i, i + 1);
            if (ans.length() < str1.length()) {
                ans = str1;
            }
            if (ans.length() < str2.length()) {
                ans = str2;
            }
        }
        return ans;
    }

    private String hwc(String str, int left, int right) {
        while (left >= 0 && right < str.length()) {
            if (str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
                continue;
            }
            break;
        }
        return str.substring(left + 1, right);
    }

    public static void main(String[] args) {
        N0005_最长回文子串 cla = new N0005_最长回文子串();
        String ans = cla.longestPalindrome("babad");
        System.out.println("ans = " + ans);
    }
}
