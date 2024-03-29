package com.icboluo.leetcode.最长回文串;

/**
 * @author icboluo
 * @since 2022-10-31 13:17
 */
class N0005_最长回文子串 {
    public static void main(String[] args) {
        N0005_最长回文子串 cla = new N0005_最长回文子串();
        String str = "babad";
        String s = cla.longestPalindrome1(str);
        System.out.println("s = " + s);
    }

    /**
     * TODO 马拉车、中心拓扑、动态规划
     * 超时
     * 判断所有子串，时间复杂度达到3次方阶
     *
     * @param str
     * @return
     */
    public String longestPalindrome1(String str) {
        // 针对于单元素的处理方式
        String res = str.substring(0, 1);
        int max = Integer.MIN_VALUE;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                String temp = str.substring(i, j + 1);
                String reverse = new StringBuilder(temp).reverse().toString();
                if (temp.length() > max && temp.equals(reverse)) {
                    max = temp.length();
                    res = temp;
                }
            }
        }
        return res;
    }

    /**
     * 从中间向两边扩张，时间复杂度平方阶
     *
     * @param str
     * @return
     */
    public String longestPalindrome2(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            // 奇数情况
            String str1 = centerMaxHwc(str, i, i);
            String str2 = centerMaxHwc(str, i, i + 1);
            res = str1.length() > res.length() ? str1 : res;
            res = str2.length() > res.length() ? str2 : res;

            // 也可以这样写
/*            if (res.length() < str1.length()) {
                res = str1;
            }
            if (res.length() < str2.length()) {
                res = str2;
            }*/
        }
        return res;
    }

    /**
     * 以left和right为中心的最长回文串
     *
     * @param str
     * @param left
     * @param right
     * @return
     */
    private String centerMaxHwc(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }

    /**
     * 中心开花向两边扩展
     *
     * @param str
     * @return
     */
    public String longestPalindrome3(String str) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < str.length(); i++) {
            int len1 = center(str, i, i);
            int len2 = center(str, i, i + 1);
            int len = Math.max(len1, len2);
            // 这里需要+1
            if (len > right - left + 1) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return str.substring(left, right + 1);
    }

    private int center(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
