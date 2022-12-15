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
        boolean haveFuHaoWei = false;
        for (char aChar : chars) {
            if (aChar == ' ') {
                continue;
            }
            if (!haveFuHaoWei) {
                if (aChar == '-') {
                    sb.append('-');
                    haveFuHaoWei = true;
                    continue;
                } else if (aChar == '+') {
                    haveFuHaoWei = true;
                    continue;
                }
            }
            if (aChar == '-' || aChar == '+') {
                break;
            } else if (Character.isDigit(aChar)) {
                sb.append(aChar);
                haveDigit = true;
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
