package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-30 0:28
 */
class N1309_解密字符串 {
    /**
     * 三位数和1位数转换规则不同
     *
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        String res = "";
        for (int i = 0; i < s.length(); ) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                int temp = Integer.parseInt(s.substring(i, i + 2));
                res += String.valueOf((char) ('j' + temp - 10));
                i += 3;
            } else {
                int temp = Integer.parseInt(s.substring(i, i + 1));
                res += String.valueOf((char) ('a' + temp - 1));
                i++;
            }
        }
        return res;
    }
}
