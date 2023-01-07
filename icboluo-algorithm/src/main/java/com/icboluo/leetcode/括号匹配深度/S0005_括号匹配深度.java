package com.icboluo.leetcode.括号匹配深度;

/**
 * @author icboluo
 * @since 2022-10-27 13:43
 */
class S0005_括号匹配深度 {
    public static void main(String[] args) {
        S0005_括号匹配深度 cla = new S0005_括号匹配深度();
        String str = "(())";
        int ans = cla.m5(str);
        System.out.println("ans = " + ans);
    }

    private int m5(String str) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                count++;
                max = Math.max(max, count);
            } else {
                count--;
            }
        }
        return max;
    }
}
