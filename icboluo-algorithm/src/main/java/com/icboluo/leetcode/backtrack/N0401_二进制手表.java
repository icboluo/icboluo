package com.icboluo.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @see N0401_二进制手表 .ts 提交
 */
class N0401_二进制手表 {
    public List<String> readBinaryWatch(int turnedOn) {
        int[] nums1 = new int[] {8, 4, 2, 1};
        int[] nums2 = new int[] {32, 16, 8, 4, 2, 1};
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn; i++) {
            List<Integer> list1 = generate(nums1, i);
            List<Integer> list2 = generate(nums2, turnedOn - i);
            for (Integer num1 : list1) {
                if (num1 >= 12) {
                    continue;
                }
                for (Integer num2 : list2) {
                    if (num2 >= 60) {
                        continue;
                    }
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    List<Integer> res;

    LinkedList<Integer> track;

    // 求数组元素和的组合
    private List<Integer> generate(int[] arr, int count) {
        res = new ArrayList<>();
        track = new LinkedList<>();
        backtrack(arr, count, 0);
        return res;
    }

    private void backtrack(int[] arr, int count, int pos) {
        if (count == 0) {
            res.add(track.stream().mapToInt(Integer::intValue).sum());
            return;
        }
        for (int i = pos; i < arr.length; i++) {
            track.add(arr[i]);
            // 其实这块也可以直接将路径直接当做参数传递进去，相当于深拷贝，就不用回溯了
            // backtrack(arr, count - 1, i + 1, sum + arr[i]);
            backtrack(arr, count - 1, i + 1);
            track.removeLast();
        }
    }
}
