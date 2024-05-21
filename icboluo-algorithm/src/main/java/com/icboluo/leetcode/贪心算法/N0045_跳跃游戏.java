package com.icboluo.leetcode.贪心算法;

/**
 * @author icboluo
 * @since 2023-06-05 23:30
 */
class N0045_跳跃游戏 {
    // 从0到最终索引最小的跳跃时间
    // BFS 难以理解 需要打❤
    public int jump1(int[] nums) {
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

    // 2个方法使用的均为贪心原则，贪心原则使用的前提就是需要证明最优解的正确性
    public static int jump2(int[] nums) {
        int curMax = 0;
        int curEnd = 0;
        int level = 0;
        // 不需要到达最后一个点
        for (int i = 0; i < nums.length - 1; i++) {
            curMax = Math.max(curMax, nums[i] + i);
            // 到达本层的边界
            if (i == curEnd) {
                level++;
                curEnd = curMax;
            }
        }
        return level;
    }
}
