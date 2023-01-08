package com.icboluo.leetcode.简单动态规划;

/**
 * @author icboluo
 * @since 2023-01-08 14:14
 */
class N0509_斐波那契数列 {
    /**
     * 这个东西就是为了说明重叠子问题
     *
     * @param cur
     * @return
     */
    public int fib1(int cur) {
        // 特例
        if (cur == 0) {
            return 0;
        }
        if (cur == 1 || cur == 2) {
            return 1;
        }
        return fib1(cur - 1) + fib1(cur - 2);
    }

    /**
     * 备忘录解法/缓存解法/哈希，数组解法，极大的减少了子问题
     * 递归是自顶向下，动态规划是自下向上 ERROR
     *
     * @param cur
     * @return
     */
    public int fib2(int cur) {
        int[] arr = new int[cur + 1];
        if (cur == 0) {
            return 0;
        }
        if (cur == 1 || cur == 2) {
            return 1;
        }
        int res = 0;
        arr[cur] = fib1(cur - 1) + fib1(cur - 2);
        return res;
    }

    /**
     * 动态规划
     *
     * @param cur
     * @return
     */
    public int fib3(int cur) {
        if (cur == 0) {
            return 0;
        }
        if (cur == 1 || cur == 2) {
            return 1;
        }
        int[] dp = new int[cur + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        // dp[3]=dp[2]+dp[1]; 状态转移方程
        for (int i = 2; i <= cur; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[cur];
    }

    /**
     * 不需要数组存储那么多的值，可以把dp table 压缩一下
     *
     * @param cur
     * @return
     */
    public int fib4(int cur) {
        if (cur == 0) {
            return 0;
        }
        int ppre = 0;
        int pre = 1;
        for (int i = 2; i <= cur; i++) {
            int sum = ppre + pre;
            ppre = pre;
            pre = sum;
        }
        return pre;
    }
}
