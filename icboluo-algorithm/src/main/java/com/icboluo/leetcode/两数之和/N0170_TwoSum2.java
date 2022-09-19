package com.icboluo.leetcode.两数之和;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-09-20 0:08
 */
public class N0170_TwoSum2 {
    private Set<Integer> sum = new HashSet<>();
    private List<Integer> nums = new ArrayList<>();

    private void add(int a) {
        for (Integer num : nums) {
            sum.add(num + a);
        }
        nums.add(a);
    }

    /**
     * 频繁访问find的时候时间复杂度只有1，但是add复杂度增加了
     *
     * @param target
     * @return
     */
    private boolean find(int target) {
        return sum.contains(target);
    }
}
