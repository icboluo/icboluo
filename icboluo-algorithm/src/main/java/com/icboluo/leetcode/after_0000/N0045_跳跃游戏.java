package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2023-06-05 23:30
 */
class N0045_跳跃游戏 {
    // 从0到最终索引最小的跳跃时间
    // BFS 难以理解 需要打❤
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int curMax = 0;
        int nextMax = 0;
        int i = 0;
        int level = 0;
        // 层级遍历，并且确定遍历上限
        while (curMax - i + 1 > 0) {
            level++;
            // 每层遍历，我们需要得出，从一个区间|层级，跳到另一个区间|层级的最大距离
            while (i <= curMax) {
                nextMax = Math.max(nextMax, nums[i] + i);
                if (nextMax >= nums.length - 1) {
                    return level;
                }
                i++;
            }
            curMax = nextMax;
        }
        return 0;
    }

    // 2个方法均使用的是贪心原则
    public int jump2(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int curMax = 0;
        int nextMax = 0;
        int i = 0;
        int level = 0;
        // 层级遍历，并且确定遍历上限
        while (curMax - i + 1 > 0) {
            level++;
            // 每层遍历，我们需要得出，从一个区间|层级，跳到另一个区间|层级的最大距离
            while (i <= curMax) {
                nextMax = Math.max(nextMax, nums[i] + i);
                if (nextMax >= nums.length - 1) {
                    return level;
                }
                i++;
            }
            curMax = nextMax;
        }
        return 0;
    }
}
