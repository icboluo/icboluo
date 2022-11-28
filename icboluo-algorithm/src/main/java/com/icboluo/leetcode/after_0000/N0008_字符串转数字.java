package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-26 14:14
 */
class N0008_字符串转数字 {
    public static void main(String[] args) {
        N0008_字符串转数字 cla = new N0008_字符串转数字();
        int i = cla.myAtoi("-91283472332");
        System.out.println("i = " + i);
    }

    // TODO ERROR 越界
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean haveDigit = false;
        for (char aChar : chars) {
            if (aChar == ' ') {
                continue;
            }
            if (aChar == '-') {
                sb.append('-');
            }
            if (Character.isDigit(aChar)) {
                sb.append(aChar);
                haveDigit = true;
            } else if (Character.isLetter(aChar)) {
                break;
            }
        }
        if (!haveDigit) {
            return 0;
        } else {
            return Integer.parseInt(sb.toString());
        }
    }
}
