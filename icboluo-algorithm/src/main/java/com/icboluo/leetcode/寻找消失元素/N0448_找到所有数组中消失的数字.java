package com.icboluo.leetcode.寻找消失元素;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-11-26 17:28
 */
class N0448_找到所有数组中消失的数字 {
    public List<Integer> findDisappearedNumbers(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(i + 1);
        }
        for (int item : arr) {
            set.remove(item);
        }
        return new ArrayList<>(set);
    }
}
