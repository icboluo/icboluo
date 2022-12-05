package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-26 14:14
 */
class N0008_字符串转数字 {
    public static void main(String[] args) {
        N0008_字符串转数字 cla = new N0008_字符串转数字();
        int i = cla.myAtoi("   -42");
        System.out.println("i = " + i);
    }

    // TODO ERROR 小数点错误
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean haveDigit = false;
        for (char aChar : chars) {
            if (aChar == ' ' || aChar == '+') {
                continue;
            }
            if (aChar == '-') {
                sb.append('-');
            } else if (Character.isDigit(aChar)) {
                sb.append(aChar);
                haveDigit = true;
                // 不能这么写，是除数字的所有，并不是字母
                // } else if (Character.isLetter(aChar)) {
            } else {
                break;
            }
        }
        if (!haveDigit) {
            return 0;
        } else {
            long l = Long.parseLong(sb.toString());
            if ((int) l == l) {
                return (int) l;
            }
            return l > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }
}
