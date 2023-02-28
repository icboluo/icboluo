package com.icboluo.leetcode.othersource;

import com.icboluo.util.IcBoLuoException;

/**
 * @author icboluo
 * @since 2022-11-01 13:04
 */
class S0006_把字符串转换成整数 {
    public static void main(String[] args) {
        var cla = new S0006_把字符串转换成整数();
        String str = "-12312312";
        int i = cla.m(str);
        System.out.println("i = " + i);
    }

    private int m(String str) {
        if (str.length() == 0) {
            return 0;
        }
        char c = str.charAt(0);
        int flag = 0;
        if (c == '+') {
            flag = 1;
        } else if (c == '-') {
            flag = 2;
        }
        int left = flag == 0 ? 0 : 1;
        int res = 0;
        for (int i = left; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (!Character.isDigit(temp)) {
                throw new IcBoLuoException("非法数据");
            }
            res = 10 * res + (temp - '0');
        }
        return flag == 2 ? -res : res;
    }
}
