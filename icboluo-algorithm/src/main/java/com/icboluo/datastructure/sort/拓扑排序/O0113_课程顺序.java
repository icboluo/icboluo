package com.icboluo.datastructure.sort.拓扑排序;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-07-10 20:42
 */
public class O0113_课程顺序 {

    public static void main(String[] args) {
        O0113_课程顺序 cla = new O0113_课程顺序();
        Integer[][] arr1 = new Integer[][]{{1, 2}, {1, 3}, {2, 3}, {3, null}, {3, 4}, {4, 5}};
        int[] sort = cla.sort(5, arr1);
        System.out.println(Arrays.toString(sort));
    }

    private int[] sort(int total, Integer[][] arrs) {
        Map<Integer, List<Integer>> eleNeedMap = new HashMap<>();
        int[] count = new int[total];
        for (Integer[] arr : arrs) {
            if (arr[1] == null) {
                continue;
            }
            eleNeedMap.computeIfAbsent(arr[1], key -> new LinkedList<>()).add(arr[0]);
            count[arr[0] - 1]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                queue.add(i + 1);
            }
        }
        int[] ans = new int[total];
        int idx = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans[idx++] = poll;
            List<Integer> subList = eleNeedMap.get(poll);
            if (subList == null) {
                continue;
            }
            for (Integer sub : subList) {
                count[sub - 1]--;
                if (count[sub - 1] == 0) {
                    queue.add(sub);
                }
            }
        }
        if (idx == total) {
            return ans;
        }
        return new int[total];
    }
}
