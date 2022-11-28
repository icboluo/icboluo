package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2022-11-26 14:08
 */
class N0007_反转整数 {
    // TODO ERROR 数字过大
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean xiaoYu0 = false;
        if (x < 0) {
            x = -x;
            xiaoYu0 = true;
        }
        StringBuilder sb = new StringBuilder();
        if (xiaoYu0) {
            sb.append("-");
        }
        boolean notFirst0 = false;
        while (x > 0) {
            int m = x % 10;
            if (notFirst0) {
                sb.append(m);
            }else{
                if (m != 0) {
                    notFirst0 = true;
                    sb.append(m);
                }
            }
            x = x / 10;
        }
        return Integer.parseInt(sb.toString());
    }
}
