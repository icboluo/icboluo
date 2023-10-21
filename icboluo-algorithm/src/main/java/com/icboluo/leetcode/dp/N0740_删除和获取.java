package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @since 2023-10-21 20:52
 */
class N0740_删除和获取 {
    public int deleteAndEarn(int[] nums) {
        int[] valArr = new int[10001];
        for (int num : nums) {
            // 元素出现次数的总和
            valArr[num] += num;
        }
        // 对于每一个元素只有获取和跳过2种操作
        int take = 0;
        int skip = 0;
        for (int i = 0; i < 10001; i++) {
            // 选择当前元素的时候，操作总和就是上一个跳过的值+当前的总和
            int takeI = skip + valArr[i];
            // 跳过当前元素的时候，结果就是上一个跳过的值和上一个获取值的最优值
            int skipI = Math.max(skip, take);
            take = takeI;
            skip = skipI;
        }
        return Math.max(take, skip);
    }
}
