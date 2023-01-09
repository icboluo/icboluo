package com.icboluo.leetcode.after_1300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-01-07 17:02
 */
class N1437_2365_检查所有的1是否相隔至少k个位置 {
    public static void main(String[] args) {
        N1437_2365_检查所有的1是否相隔至少k个位置 cla = new N1437_2365_检查所有的1是否相隔至少k个位置();
        boolean b = cla.kLengthApart(new int[]{1, 0, 0, 0, 1, 0, 0, 1}, 2);
        System.out.println("b = " + b);
    }

    /**
     * 二进制数组所有1之间的距离是否均大于k FIXME ERROR
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApart(int[] nums, int k) {
        int left = -1;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                // 第一个1出现时候的特例
                if (left == -1) {
                    left = right;
                }
                if (right - left < k) {
                    return false;
                }
                left = right++;
            }
        }
        return true;
    }

    /**
     * 2365 任务计划程序2
     *
     * @param tasks 下一个任务类型
     * @param space 完成一项任务之后最少经过多少天才可以执行同类型的任务
     * @return
     */
    public long taskSchedulerII1(int[] tasks, int space) {
        // 这里面记录的是完成时间
        Map<Integer, Long> taskFinishMap = new HashMap<>();
        long day = 0;
        // 对于此循环来说，主导者是tasks，不要太多拉入day进行比较
        for (int task : tasks) {
            if (taskFinishMap.containsKey(task)) {
                // 完成时间+等待时间和当前时间做比较
                day = Math.max(day, taskFinishMap.get(task) + space) + 1;
                taskFinishMap.put(task, day);
            } else {
                taskFinishMap.put(task, ++day);
            }
        }
        return day;
    }

    public long taskSchedulerII2(int[] tasks, int space) {
        // 这里面记录的是完成时间+等待时间
        Map<Integer, Long> taskFinishMap = new HashMap<>();
        long day = 0;
        // 对于此循环来说，主导者是tasks，不要太多拉入day进行比较
        for (int task : tasks) {
            // 完成天数
            day = Math.max(day + 1, taskFinishMap.getOrDefault(task, 0L));
            taskFinishMap.put(task, day + space + 1);
        }
        return day;
    }
}
