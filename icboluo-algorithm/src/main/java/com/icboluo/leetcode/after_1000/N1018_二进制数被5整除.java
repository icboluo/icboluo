package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-08 0:39
 */
class N1018_二进制数被5整除 {
    // FIXME ERROR
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> list = new ArrayList<>();
        int cur = 0;
        for (int num : nums) {
            cur = cur * 2 + num;
            list.add(cur % 5 == 0);
        }
        return list;
    }
}
