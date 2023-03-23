package com.icboluo.leetcode.二维数组;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2023-03-24 0:39
 */
class N1672_最富有的客户财富 {
    /**
     * 返回二维数组每行和的最大值
     *
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {
        return Arrays.stream(accounts).mapToInt(row -> Arrays.stream(row).sum()).max().orElse(0);
    }
}
