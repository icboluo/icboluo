package com.icboluo.leetcode.after_0200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-14 14:37
 */
class N0228_汇总范围 {
    /**
     * 把数组连续的部分分成一组
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        int pre = 0;
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i - 1] + 1 != nums[i]) {
                if (nums[pre] == nums[i - 1]) {
                    res.add(nums[pre] + "");
                } else {
                    res.add(nums[pre] + "->" + nums[i - 1]);
                }
                pre = i;
            }
        }
        return res;
    }
}
