package com.icboluo.leetcode.建模;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-07 22:45
 */
 class N1389_仿照ArrayList增加方法 {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
