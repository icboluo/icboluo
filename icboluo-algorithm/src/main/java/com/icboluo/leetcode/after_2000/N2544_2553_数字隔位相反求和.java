package com.icboluo.leetcode.after_2000;

import java.util.stream.Stream;

/**
 * @author icboluo
 * @since 2023-06-12 0:43
 */
class N2544_2553_数字隔位相反求和 {
    public int alternateDigitSum(int n) {
        int res = 0;
        // 当然，time修改为 1|-1也是可以的
        int time = 0;
        // 难点：我们方便的是从右到左，但是不能确定最后一位的符号（所以我们最后修正结果即可
        while (n > 0) {
            if (time % 2 == 0) {
                res += n % 10;
            } else {
                res -= n % 10;
            }
            n = n / 10;
            time++;
        }
        // 0|1
        if (time % 2 == 0) {
            res = -res;
        }
        return res;
    }

    // 2553 分隔数组中的数字,拆成一位一位的
    public static int[] separateDigits(int[] nums) {
        // 因为对每个数字我们只能做到逆序，所以我们把整体逆序，然后再反转
        return Stream.iterate(nums.length - 1, i -> --i)
                .limit(nums.length)
                .map(i -> nums[i])
                .<Integer>mapMulti((a, ca) -> {
                    while (a > 0) {
                        ca.accept(a % 10);
                        a /= 10;
                    }
                })
                .sorted((a, b) -> -1)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
