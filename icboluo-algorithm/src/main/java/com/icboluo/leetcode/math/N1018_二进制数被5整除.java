package com.icboluo.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-08 0:39
 */
class N1018_二进制数被5整除 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> list = new ArrayList<>();
        int cur = 0;
        for (int num : nums) {
            cur = cur * 2 + num;
            list.add(cur % 5 == 0);
            // 看看这一行，可以有效的缩减当前的元素大小，防止溢出
            // 我们需要证明，能被5整除，再不断的*2，结果并不会产生新的5...我证明不了
            cur = cur % 5;
        }
        return list;
    }
}
