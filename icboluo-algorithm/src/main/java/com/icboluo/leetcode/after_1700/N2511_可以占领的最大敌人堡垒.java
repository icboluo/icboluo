package com.icboluo.leetcode.after_1700;

/**
 * @author icboluo
 * @since 2023-01-15 18:27
 */
class N2511_可以占领的最大敌人堡垒 {
    /**
     * 将1移动到-1的位置，中间经过的所有0的最大值
     * 其实就是求两个相邻1，-1之间的距离
     *
     * @param forts 只有 -1 0 1,
     * @return
     */
    public int captureForts(int[] forts) {
        int res = 0;
        int left = 0, right = 0;
        while (right < forts.length) {
            if (forts[right] != 0) {
                // 如果异号
                if (forts[left] == -forts[right]) {
                    res = Math.max(res, right - left - 1);
                }
                left = right;
            }
            right++;
        }
        return res;
    }
}
