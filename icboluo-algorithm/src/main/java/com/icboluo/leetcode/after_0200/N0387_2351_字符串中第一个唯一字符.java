package com.icboluo.leetcode.after_0200;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author icboluo
 * @since 2022-11-02 20:24
 */
class N0387_2351_字符串中第一个唯一字符 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // N2351 出现2次的第一封信
    public char repeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                return ch;
            } else {
                set.add(ch);
            }
        }
        return ' ';
    }

    // 0451 按频率对字符排序
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        // 如果要按照原有的顺序遍历，则仅仅简单map结构存储不足，需要扩展；在这里，内部类是一个好的处理方式
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        // 当然，此块用优先级队列进行排序也是可以的，将map元素放进队列中；然后队列poll进行遍历拼接结果值
        return map.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).map(a -> {
            String temp = "";
            for (int i = 0; i < a.getValue(); i++) {
                temp += a.getKey();
            }
            return temp;
        }).collect(Collectors.joining());
    }

    // 0347 前k个频率元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // 1636 按增加频率对数组进行排序
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<int[]> collect = map.entrySet().stream().sorted((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return a.getValue() - b.getValue();
            }
            return b.getKey() - a.getKey();
        }).map(a -> {
            int[] ints = new int[a.getValue()];
            Arrays.fill(ints, a.getKey());
            return ints;
        }).toList();
        List<Integer> integers = new ArrayList<>();
        for (int[] ints : collect) {
            for (int anInt : ints) {
                integers.add(anInt);
            }
        }
        // 从map到这里可以替换为普通for来处理，比较简单
        return integers.stream().mapToInt(Integer::intValue).toArray();
    }

    // 2278 字符串字母中的百分比
    public int percentageLetter(String s, char letter) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (letter == s.charAt(i)) {
                count++;
            }
        }
        return 100 * count / s.length();
    }

    // 2341 数组中的最大对数
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
                count++;
            } else {
                map.put(num, 1);
            }
        }
        return new int[]{count, map.size()};
    }

    // 2374 节点的边最高得分 TODO 超时
    public int edgeScore(int[] edges) {
        // 入度
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.computeIfAbsent(edges[i], key -> new ArrayList<>()).add(i);
        }
        return map.entrySet()
                .stream()
                .sorted((a, b) ->
                        b.getValue().stream().map(BigDecimal::new).reduce(BigDecimal::add).get()
                                .compareTo(a.getValue().stream().map(BigDecimal::new).reduce(BigDecimal::add).get()))
                .map(Map.Entry::getKey).findFirst().orElse(-1);
    }

    // 2404 最常见的偶数元素
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return map.entrySet().stream().sorted((a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            }
            return a.getKey() - b.getKey();
        }).map(Map.Entry::getKey).findFirst().orElse(-1);
    }
}
