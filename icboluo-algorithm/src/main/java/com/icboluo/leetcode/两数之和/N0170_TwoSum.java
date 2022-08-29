package com.icboluo.leetcode.两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-08-29 22:03
 */
public class N0170_TwoSum {

    /**
     * 记录元素出现次数
     */
    private final Map<Integer, Integer> map = new HashMap<>();

    public void add(int a) {
        map.put(a, map.getOrDefault(a, 0) + 1);
    }

    public boolean find(int target) {

    }
}
