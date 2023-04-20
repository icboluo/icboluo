package com.icboluo.leetcode.after_2000;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author icboluo
 * @since 2023-04-20 22:50
 */
class N2363_合并相似项目 {
    /**
     * 相同价值的物品重量，结果按升序排列
     *
     * @param items1
     * @param items2
     * @return
     */
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        // TreeMap key有序的map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] item : items1) {
            map.merge(item[0], item[1], Integer::sum);
        }
        for (int[] item : items2) {
            map.merge(item[0], item[1], Integer::sum);
        }
        return map.entrySet().stream().map(e -> Arrays.asList(e.getKey(), e.getValue())).toList();
    }
}
