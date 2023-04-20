package com.icboluo.leetcode.after_2000;

/**
 * @author icboluo
 * @since 2023-04-20 23:16
 */
class N2432_完成最长任务的员工 {
    /**
     * n个员工 id从0开始
     *
     * @param n
     * @param logs arr[i][0]是员工id，arr[i][1]是完成任务需要的时间
     * @return
     */
    public int hardestWorker(int n, int[][] logs) {
        int max = logs[0][1];
        int res = logs[0][0];
        for (int i = 1; i < logs.length; i++) {
            int[] cur = logs[i];
            // 当前任务的时间比较大，记录res
            if (cur[1] - logs[i - 1][1] > max) {
                max = cur[1] - logs[i - 1][1];
                res = cur[0];
                // 相等取最小
            } else if (cur[1] - logs[i - 1][1] == max) {
                res = Math.min(res, logs[i][0]);
            }
        }
        return res;
    }
}
