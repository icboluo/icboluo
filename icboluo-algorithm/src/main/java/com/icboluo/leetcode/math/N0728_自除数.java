package com.icboluo.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-04-20 22:06
 */
class N0728_自除数 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int temp = i;
            while (temp > 0) {
                // 首先，所有位数均不能为0，其次...
                if (temp % 10 == 0 || i % (temp % 10) != 0) {
                    break;
                }
                temp = temp / 10;
            }
            if (temp == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
