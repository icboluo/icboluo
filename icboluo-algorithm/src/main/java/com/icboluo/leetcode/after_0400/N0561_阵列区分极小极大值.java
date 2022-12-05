package com.icboluo.leetcode.after_0400;

import java.util.Arrays;

/**
 * @author icboluo
 * @since 2022-12-05 21:14
 */
class N0561_阵列区分极小极大值 {
    public int arrayPairSum(int[] nums) {
        // 先极小再极大，每一组数据中，较大的值将被忽略，我们期望尽可能忽略的值是最近的，这样总的结果才可能最优，所以需要排序
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
