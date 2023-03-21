package com.icboluo.leetcode.元素出现次数;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2023-03-21 23:26
 */
class N2148_排序看中间的元素有多少个 {
    public int countElements(int[] nums) {
        IntSummaryStatistics iss = Arrays.stream(nums).sorted().boxed().collect(Collectors.summarizingInt(ele -> ele));
        return (int) Arrays.stream(nums).filter(ele -> ele > iss.getMin() && ele < iss.getMax()).count();
    }

    /**
     * N0744 查询大于目标的最小字母 low
     * 非降序数组，找出目标值的下一个字符，如果没有，返回第一个字符
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return letters[0];
    }
}
