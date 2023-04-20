package com.icboluo.leetcode.after_1700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2023-02-21 21:32
 */
class N1742_盒子中小球的最大数量 {
    // 最小值到最大值直接位数相加
    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> eleCountMap = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int temp = i;
            int sum = 0;
            while (temp >= 10) {
                sum += temp % 10;
                temp /= 10;
            }
            sum += temp;
            eleCountMap.put(sum, eleCountMap.getOrDefault(sum, 0) + 1);
        }
        return eleCountMap.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    }
}
