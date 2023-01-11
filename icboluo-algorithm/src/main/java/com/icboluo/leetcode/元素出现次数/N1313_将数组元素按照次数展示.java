package com.icboluo.leetcode.元素出现次数;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author icboluo
 * @since 2022-12-28 21:20
 */
class N1313_将数组元素按照次数展示 {
    public static void main(String[] args) {
        N1313_将数组元素按照次数展示 cla = new N1313_将数组元素按照次数展示();
        int[] res = cla.decompressRLElist(new int[]{73, 95, 5, 68, 6, 14, 98, 3, 98, 39, 100, 69, 76, 77, 93, 46, 91, 69, 26, 13, 30, 53, 15, 53, 34, 17});
        System.out.println(Arrays.toString(res));
    }

    /**
     * it`s error
     *
     * @param nums
     * @return
     */
    public int[] decompressRLElist1(int[] nums) {
        // 我们不能仅仅使用map来做这件事，因为key是可以重复的
        Map<Integer, Integer> eleCountMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i += 2) {
            eleCountMap.put(nums[i + 1], nums[i]);
        }
        return eleCountMap.entrySet().stream()
                .map(entry -> {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < entry.getValue(); i++) {
                        list.add(entry.getKey());
                    }
                    return list;
                }).flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int[] decompressRLElist(int[] nums) {
        return IntStream.range(0, nums.length / 2).mapToObj(i -> {
            int key = nums[i * 2 + 1];
            int val = nums[i * 2];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < val; j++) {
                list.add(key);
            }
            return list;
        }).flatMap(Collection::stream).mapToInt(Integer::intValue).toArray();
    }
}
