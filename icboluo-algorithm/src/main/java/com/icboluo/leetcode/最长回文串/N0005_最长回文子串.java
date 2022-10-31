package com.icboluo.leetcode.最长回文串;

/**
 * @author icboluo
 * @since 2022-10-31 13:17
 */
class N0005_最长回文子串 {
    public static void main(String[] args) {
        N0005_最长回文子串 cla = new N0005_最长回文子串();
        String str = "babad";
        String s = cla.maxHwc1(str);
        System.out.println("s = " + s);
    }

    /**
     * 判断所有子串，时间复杂度达到3次方阶
     *
     * @param str
     * @return
     */
    private String maxHwc1(String str) {
        String res = "";
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
    private String maxHwc2(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            // 奇数情况
            String str1 = centerMaxHwc(str, i, i);
            String str2 = centerMaxHwc(str, i, i + 1);
            res = str1.length() > res.length() ? str1 : res;
            res = str2.length() > res.length() ? str2 : res;
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
}
