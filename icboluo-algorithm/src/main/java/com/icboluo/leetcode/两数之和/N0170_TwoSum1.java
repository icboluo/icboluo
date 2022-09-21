package com.icboluo.leetcode.两数之和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-08-29 22:03
 */
class N0170_TwoSum1 {

    /**
     * 记录元素出现次数
     */
    private final Map<Integer, Integer> map = new HashMap<>();

    public void add(int a) {
        map.put(a, map.getOrDefault(a, 0) + 1);
    }

    public boolean find(int target) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int need = target - entry.getKey();
            if (map.containsKey(need)) {
                // TODO 后面的判断是什么意思
                if (map.get(need) > 1 || !map.get(need).equals(entry.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }
}
