package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-22 21:15
 */
class N0997_被相信次数 {
    // TODO ERROR
    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1];
        for (int[] ints : trust) {
            count[ints[0]]--;
            count[ints[1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public int findJudge2(int n, int[][] trust) {
        int[] count = new int[n + 1];
        for (int[] ints : trust) {
            if (ints[0] != ints[1]) {
                count[ints[1]]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
