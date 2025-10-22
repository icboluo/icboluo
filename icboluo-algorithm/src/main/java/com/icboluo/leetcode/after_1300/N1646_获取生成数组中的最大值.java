package com.icboluo.leetcode.after_1300;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2025-10-21 21:51
 */
class N1646_获取生成数组中的最大值 {
    public static void main(String[] args) {
        var cla = new N1646_获取生成数组中的最大值();
        System.out.println(cla.getMaximumGenerated(7));
    }

    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
// dp[0] 和 dp[1]都有值，下一个应该是dp[2]，计算可得，i的初始值应该是1
// i*2<=n 这个约束条件比除法更简单
        for (int i = 1; i * 2 <= n; i++) {
            if (2 * i <= n) {
                dp[2 * i] = dp[i];
            }
            if (2 * i + 1 <= n) {
                dp[2 * i + 1] = dp[i] + dp[i + 1];
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
