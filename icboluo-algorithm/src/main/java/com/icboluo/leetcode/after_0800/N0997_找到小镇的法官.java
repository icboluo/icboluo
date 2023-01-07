package com.icboluo.leetcode.after_0800;

/**
 * @author icboluo
 * @since 2022-11-22 21:15
 */
class N0997_找到小镇的法官 {
    // 找到镇长 镇长不相信任何人，其他人都相信镇长；n个人
    public int findJudge1(int n, int[][] trust) {
        if (trust.length == 0 && n == 1) {
            return 1;
        }
        // 被相信次数
        int[] count = new int[n + 1];
        for (int[] ints : trust) {
            count[ints[0]]--;
            count[ints[1]]++;
        }
        // 这里计算的是count，应该用count.length 而不是用n
        for (int i = 0; i < count.length; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    // FIXME ERROR
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
