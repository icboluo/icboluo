package com.icboluo.leetcode.after_0000;

/**
 * @author icboluo
 * @since 2023-06-13 4:26
 */
class N0055_跳跃游戏 {
    // 是否可以达到最后一个
    public boolean canJump(int[] nums) {
        int max = nums.length - 1;
        // 反向循环
        for (int i = nums.length - 2; i >= 0; i--) {
            // 如果有一个点，能跳跃到最后一个点
            if (i + nums[i] >= max) {
                // 则最大值修改为当前点
                max = i;
            }
        }
        // 其实就是==0，判断一下能否从right-left而已，很简单的
        return max == 0;
    }
}
