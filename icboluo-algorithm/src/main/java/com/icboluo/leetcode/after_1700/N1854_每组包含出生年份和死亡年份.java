package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-03-02 21:01
 */
class N1854_每组包含出生年份和死亡年份 {
    // 前缀和
    public int maximumPopulation(int[][] logs) {
        int[] arr = new int[1001];
        for (int[] log : logs) {
            arr[log[0] - 1950]++;
            arr[log[1] - 1950]--;
        }
        int max = arr[0];
        int maxYear = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] > max) {
                max = arr[i];
                maxYear = i;
            }
        }
        return maxYear + 1950;
    }
}
