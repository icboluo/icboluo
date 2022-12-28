package com.icboluo.leetcode.after_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-12-28 20:43
 */
class N1295_2094_数组长度为偶数位的个数 {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if ((num + "").length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 2094 查找3位偶数
     * 数组里面随便挑元素，可以构成3位偶数即可：排列问题
     *
     * @param digits
     * @return
     */
    public int[] findEvenNumbers(int[] digits) {
        used = new boolean[digits.length];
        Arrays.sort(digits);
        combine(digits);
        return res.stream()
                .map(li -> li.stream().map(String::valueOf).reduce("", (a, b) -> a + b))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private void combine(int[] arr) {
        if (!track.isEmpty() && track.getFirst() == 0) {
            return;
        }
        if (track.size() == 3) {
            if (track.getLast() % 2 == 0) {
                res.add(new ArrayList<>(track));
            }
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i != 0 && arr[i] == arr[i - 1] && !used[i - 1]) {
                continue;
            }
            track.add(arr[i]);
            used[i] = true;
            combine(arr);
            used[i] = false;
            track.removeLast();
        }
    }

    private LinkedList<Integer> track = new LinkedList<>();

    private List<List<Integer>> res = new ArrayList<>();

    private boolean[] used;
}
