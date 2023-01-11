package com.icboluo.leetcode.元素出现次数;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author icboluo
 * @since 2023-01-07 23:15
 */
class N1370_递增递减字符串 {
    /**
     * 从小到达，从大到小，再从小到大;相同频率的顺序一致；这个题挺好玩
     * LinkedHashMap 可以保证存取顺序一致问题；
     * TreeMap 常用来解决排序问题（不可重复，不可为null，无索引）
     * 相对而言，TreeMap的使用频率应该高于 LinkedHashMap
     * 因为字母的个数是有限的，也可以使用26位数组解决这个问题
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
        TreeMap<Character, Integer> eleCountMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            eleCountMap.put(s.charAt(i), eleCountMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        boolean isAsc = true;
        StringBuilder sb = new StringBuilder();
        while (!eleCountMap.isEmpty()) {
            TreeSet<Character> treeSet = isAsc
                    ? new TreeSet<>(eleCountMap.keySet())
                    : new TreeSet<>(eleCountMap.descendingKeySet());
            for (Character ch : treeSet) {
                sb.append(ch);
                eleCountMap.put(ch, eleCountMap.get(ch) - 1);
                eleCountMap.remove(ch, 0);
            }
            isAsc = !isAsc;
        }
        return sb.toString();
    }
}
