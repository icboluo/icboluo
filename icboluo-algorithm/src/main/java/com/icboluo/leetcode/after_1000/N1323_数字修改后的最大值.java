package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2022-12-28 20:39
 */
class N1323_数字修改后的最大值 {
    public int maximum69Number(int num) {
        String str = num + "";
        String s = str.replaceFirst("6", "9");
        return Integer.parseInt(s);
    }
}
