package com.icboluo.leetcode.after_1000;

/**
 * @author icboluo
 * @since 2023-06-12 0:48
 */
class N1217_将筹码移动到同一位置的最低成本 {
    // FIXME ERROR
    public int minCostToMoveChips(int[] position) {
        // 由于纯偶数/奇数之间移动无需成本，我们先汇集，然后计算较小的即可
        int ou = 0;
        int ji = 0;
        for (int i = 0; i < position.length; i++) {
            if (i % 2 == 0) {
                ou += position[i];
            } else {
                ji += position[i];
            }
        }
        return Math.min(ou, ji);
    }
}
