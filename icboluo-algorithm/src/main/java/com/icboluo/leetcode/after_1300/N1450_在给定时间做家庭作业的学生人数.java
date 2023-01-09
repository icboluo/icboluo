package com.icboluo.leetcode.after_1300;

/**
 * @author icboluo
 * @since 2023-01-07 16:54
 */
class N1450_在给定时间做家庭作业的学生人数 {
    /**
     * @param startTime 开始做作业时间
     * @param endTime   结束做作业时间
     * @param queryTime 查询时间
     * @return 查询时间做作业的人数
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            int start = startTime[i];
            int end = endTime[i];
            if (queryTime >= start && queryTime <= end) {
                count++;
            }
        }
        return count;
    }
}
