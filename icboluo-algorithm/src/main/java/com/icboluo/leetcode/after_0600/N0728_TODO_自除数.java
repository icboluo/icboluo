package com.icboluo.leetcode.after_0600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-04-20 22:06
 */
 class N0728_TODO_自除数 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < right; i++) {
            int temp = i;
            while (temp > 0) {
                temp = temp / 10;
            }
        }
        return res;
    }
}
