package com.icboluo.leetcode.after_1000;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-06-12 0:48
 */
class N1217_将筹码移动到同一位置的最低成本 {
    /**
     * 数组并不是代表每个位置上有多少筹码，而是代表每个筹码在什么位置
     *
     * @param position
     * @return
     */
    public int minCostToMoveChips(int[] position) {
        Map<Integer, Long> eleCountMap = Arrays.stream(position)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // 由于纯偶数|奇数之间移动无需成本，我们先汇集，然后计算较小的即可
        int ou = 0;
        int ji = 0;
        for (Map.Entry<Integer, Long> entry : eleCountMap.entrySet()) {
            if (entry.getKey() % 2 == 0) {
                ou += entry.getValue();
            } else {
                ji += entry.getValue();
            }
        }
        return Math.min(ou, ji);
    }
}
