package com.icboluo.leetcode.low;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-29 19:25
 */
class N2347_最好的扑克手牌 {
    /**
     * 最佳扑克手 low
     *
     * @param ranks 牌的等级
     * @param suits 牌的花色
     * @return
     */
    public String bestHand(int[] ranks, char[] suits) {
        long count = IntStream.range(0, suits.length).mapToObj(i -> suits[i]).distinct().count();
        // 5张牌花色相同
        if (count == 1) {
            return "Flush";
        }
        Map<Integer, Long> eleCountMap = Arrays.stream(ranks)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Long value : eleCountMap.values()) {
            if (value >= 3) {
                return "Three of a Kind";
            }
        }
        if (eleCountMap.containsValue(2L)) {
            return "Pair";
        }
        return "High Card";
    }

    /**
     * 2525 根据标准对框进行分类 low FIXME
     *
     * @param length
     * @param width
     * @param height
     * @param mass   质量
     * @return
     */
    public String categorizeBox(int length, int width, int height, int mass) {
        String a = "";
        if (length >= 10000 || width >= 10000 || height >= 10000) {
            a = "Bulky";
        } else if (length * width * height >= Math.pow(10, 9)) {
            a = "Bulky";
        }
        String b = "";
        if (mass >= 100) {
            b = "Heavy";
        }
        if (a.equals("Bulky") && b.equals("Heavy")) {
            return "Both";
        }
        if (!a.equals("Bulky") && !b.equals("Heavy")) {
            return "Neither";
        }
        if (a.equals("Bulky")) {
            return "Bulky";
        }
        return "Heavy";
    }
}
