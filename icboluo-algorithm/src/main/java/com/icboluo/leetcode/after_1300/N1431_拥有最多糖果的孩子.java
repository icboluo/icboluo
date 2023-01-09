package com.icboluo.leetcode.after_1300;

import java.util.Arrays;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-07 22:11
 */
class N1431_拥有最多糖果的孩子 {
    /**
     * 暴力解
     *
     * @param candies      孩子拥有的糖果数量
     * @param extraCandies 你拥有的糖果梳理
     * @return 假如给孩子糖果后，孩子是否拥有最多的糖果数
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt() - extraCandies;
        return Arrays.stream(candies).mapToObj(num -> num >= max).toList();
    }
}
