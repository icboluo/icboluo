package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-23 23:56
 */
class N2264_字符串中最大的3位相同的数字 {
    /**
     * 连续3个字符相等
     *
     * @param num
     * @return
     */
    public String largestGoodInteger(String num) {
        String res = "";
        for (int i = 0; i < num.length() - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
                String cur = String.valueOf(num.charAt(i)).repeat(3);
                if (res.isBlank() || res.compareTo(cur) < 0) {
                    res = cur;
                }
            }
        }
        return res;
    }
}
