package com.icboluo.leetcode.math;

/**
 * @author icboluo
 * @since 2023-03-24 0:29
 */
class N1716_计算金钱 {
    /**
     * 第一天1块钱，第二天2..；第二周第一天2块，第二天3... 看不懂 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int totalMoney(int n) {
        // 有多少周
        int count7 = n / 7;
        int res = 0;
        // 一周和为28
        for (int i = 0; i < count7; i++) {
            res += 7 * (i + 3);
        }
        for (int i = 7 * count7; i < n; i++) {
            count7++;
            res += count7;
        }
        return res;
    }
}
