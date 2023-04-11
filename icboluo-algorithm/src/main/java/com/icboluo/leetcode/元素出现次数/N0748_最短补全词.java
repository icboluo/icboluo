package com.icboluo.leetcode.元素出现次数;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2023-03-30 21:50
 */
class N0748_最短补全词 {
    /**
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Integer, Integer> eleCountMap = IntStream.range(0, licensePlate.length())
                .map(licensePlate::charAt)
                .mapToObj(Character::toLowerCase)
                .filter(ch -> ch >= 'a' && ch <= 'z')
                .collect(Collectors.toMap(Function.identity(), ele -> 1, Integer::sum));
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
        for (String word : words) {
            Map<Integer, Integer> map = IntStream.range(0, word.length())
                    .map(word::charAt)
                    .mapToObj(Character::toLowerCase)
                    .collect(Collectors.toMap(Function.identity(), ele -> 1, Integer::sum));
            boolean isValid = true;
            for (Map.Entry<Integer, Integer> entry : eleCountMap.entrySet()) {
                if (entry.getValue() > map.getOrDefault(entry.getKey(), 0)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                pq.add(word);
            }
        }
        return pq.poll();
    }
}
