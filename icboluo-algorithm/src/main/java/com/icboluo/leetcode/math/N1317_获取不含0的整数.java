package com.icboluo.leetcode.math;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-28 20:30
 */
class N1317_获取不含0的整数 {
    public static void main(String[] args) {
        N1317_获取不含0的整数 cla = new N1317_获取不含0的整数();
        int[] noZeroIntegers = cla.getNoZeroIntegers(10000);
        System.out.println(Arrays.toString(noZeroIntegers));
    }

    /**
     * 把n拆分成2个整数和 ERROR
     *
     * @param n
     * @return
     */
    public int[] getNoZeroIntegers1(int n) {
        String str = n + "";
        int weiShu = 0;
        String a = "";
        String b = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            int cur = Integer.parseInt(String.valueOf(str.charAt(i))) - weiShu;
            if (cur == 0 && i == 0) {
                break;
            }
            if (cur == 0) {
                a = 1 + a;
                b = 9 + b;
                weiShu = 1;
            } else if (cur == 1) {
                a = 2 + a;
                b = 9 + b;
                weiShu = 1;
            } else {
                weiShu = 0;
                a = 1 + a;
                b = cur - 1 + b;
            }
        }
        return new int[]{Integer.parseInt(a), Integer.parseInt(b)};
    }

    /**
     * 需要好好看这个代码，写的不错的
     *
     * @param n
     * @return
     */
    public int[] getNoZeroIntegers(int n) {
        int a = 0;
        int b = 0;
        int step = 1;
        while (n > 0) {
            int m = n % 10;
            n /= 10;
            if ((m == 0 || m == 1) && n > 0) {
                a += step * (1 + m);
                b += step * 9;
                n--;
            } else {
                a += step * 1;
                b += step * (m - 1);
            }
            step *= 10;
        }
        return new int[]{a, b};
    }
}
