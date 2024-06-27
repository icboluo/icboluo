package com.icboluo.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2020-09-29 21:11
 */
class N0120_三角形最小路径和 {
    public static void main(String[] args) {
        var cla = new N0120_三角形最小路径和();
        List<Integer> a = Arrays.asList(2);
        List<Integer> b = Arrays.asList(3, 4);
        List<Integer> c = Arrays.asList(5, 6, 7);
        List<Integer> d = Arrays.asList(4, 1, 8, 3);
        System.out.println(cla.minimumTotal(Arrays.asList(a, b, c, d)));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }
        int n = triangle.get(triangle.size() - 1).size();
        // 自下而上
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> cur = triangle.get(i);
            for (int j = 0; j < cur.size(); j++) {
                dp[j] = cur.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
