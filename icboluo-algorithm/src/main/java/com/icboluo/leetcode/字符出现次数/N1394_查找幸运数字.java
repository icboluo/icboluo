package com.icboluo.leetcode.字符出现次数;

import java.util.*;

/**
 * @author icboluo
 * @since 2023-01-07 22:38
 */
class N1394_查找幸运数字 {
    /**
     * 幸运数字是频率等于其值的元素，返回最大的幸运数字
     *
     * @param arr
     * @return
     */
    public int findLucky(int[] arr) {
        TreeMap<Integer, Integer> eleCountMap = new TreeMap<>(Collections.reverseOrder());
        for (int num : arr) {
            eleCountMap.put(num, eleCountMap.getOrDefault(num, 0) + 1);
        }
        // 也可以这样写
        NavigableSet<Integer> integers = eleCountMap.descendingKeySet();
        // 也可以直接这样把map反转一下
        NavigableMap<Integer, Integer> integerIntegerNavigableMap = eleCountMap.descendingMap();
        for (Map.Entry<Integer, Integer> entry : eleCountMap.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
