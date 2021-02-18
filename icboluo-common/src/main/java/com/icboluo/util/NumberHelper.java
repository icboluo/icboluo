package com.icboluo.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 数字比较大小不要用equals，用compare
 *
 * @author icboluo
 * @date 2020/12/19 20:02
 */
public class NumberHelper {


    /**
     * 求最大公约数 辗转相除法(欧几里德算法) 例如，求（319，377）： ∵ 319÷377=0（余319）
     * ∴（319，377）=（377，319）； ∵ 377÷319=1（余58） ∴（377，319）=（319，58）； ∵
     * 319÷58=5（余29） ∴ （319，58）=（58，29）； ∵ 58÷29=2（余0） ∴ （58，29）= 29； ∴
     * （319，377）=29。 可以写成右边的格式。
     * 用辗转相除法求几个数的最大公约数，可以先求出其中任意两个数的最大公约数，再求这个最大公约数与第三个数的最大公约数，依次求下去，直到最后一个数为止。
     * 最后所得的那个最大公约数，就是所有这些数的最大公约数。
     *
     * @param m
     * @param n
     * @return
     */
    public static int GCD(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }


    /**
     * 质因数分解法：把每个数分别分解质因数，再把各数中的全部公有质因数提取出来连乘，所得的积就是这几个数的最大公约数。 (小学学的方法)
     *
     * @param m
     * @param n
     * @return
     */
    public static int PrimeGCD(int m, int n) {
        int result = 1;
        Set<Integer> set1 = getFactor(m);
        Set<Integer> set2 = getFactor(n);
        // 取交集
        set1.retainAll(set2);
        // 取最大
        result = Collections.max(set1);
        return result;
    }

    /**
     * 更相减损术”,即“可半者半之，不可半者，副置分母、子之数，以少减多，更相减损，求其等也。以等数约之。”
     *
     * @param m
     * @param n
     * @return
     */
    public static int equalGCD(int m, int n) {
        while (m != n) {
            if (m > n) {
                m -= n;
            } else {
                n -= m;
            }
        }
        return m;
    }


    /**
     * 获取某一数值的所有因数
     *
     * @param m
     * @return
     */
    private static Set<Integer> getFactor(int m) {
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= m; i++) {
            if (m % i == 0) {
                set.add(i);
            }
        }
        return set;
    }
}
