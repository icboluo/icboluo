package com.icboluo.leetcode.after_2000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-04-15 20:46
 */
class N2549_统计琢磨上的不同数字 {
    Set<Integer> set = new HashSet<>();


    public int distinctIntegers(int n) {
        set.add(n);
        for (int i = 2; i < n; i++) {
            if (n % i == 1) {
                distinctIntegers(i);
            }
        }
        return set.size();
    }
}
