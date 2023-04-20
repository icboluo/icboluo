package com.icboluo.leetcode.after_2000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-04-15 20:46
 */
class N2549_统计板上的不同数字 {
    Set<Integer> set = new HashSet<>();

    /**
     * 计算桌面上的不同数字 TODO 没看懂
     *
     * @param n
     * @return
     */
    public int distinctIntegers1(int n) {
        set.add(n);
        int size = set.size();
        for (int i = 2; i < n; i++) {
            if (n % i == 1) {
                distinctIntegers1(i);
            }
            // 判断set是否有增加数据
            if (size == set.size()) {
                break;
            }
            size = set.size();
        }
        return set.size();
    }

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
