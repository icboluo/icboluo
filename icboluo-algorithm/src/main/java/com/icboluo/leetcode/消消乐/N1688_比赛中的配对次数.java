package com.icboluo.leetcode.消消乐;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2023-03-29 23:22
 */
class N1688_比赛中的配对次数 {
    public int numberOfMatches(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return n / 2 + numberOfMatches(n / 2);
        } else {
            return (n - 1) / 2 + numberOfMatches((n + 1) / 2);
        }
    }

    Set<Integer> set = new HashSet<>();

    /**
     * 2549 计算桌面上的不同数字 FIXME ERROR
     *
     * @param n
     * @return
     */
    public int distinctIntegers(int n) {
        set.add(n);
        int size = set.size();
        for (int i = 2; i < n; i++) {
            if (n % i == 1) {
                distinctIntegers(i);
            }
            // 判断set是否有增加数据
            if (size == set.size()) {
                break;
            }
            size = set.size();
        }
        return set.size();
    }
}
