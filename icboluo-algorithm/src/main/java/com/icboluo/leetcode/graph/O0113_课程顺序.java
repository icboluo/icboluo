package com.icboluo.leetcode.graph;

import java.util.*;

/**
 * 拓扑排序
 *
 * @author icboluo
 * @since 2022-07-10 20:42
 */
class O0113_课程顺序 {

    public static void main(String[] args) {
        var cla = new O0113_课程顺序();
        Integer[][] arr1 = new Integer[][]{{1, 2}, {1, 3}, {2, 3}, {3, null}, {3, 4}, {4, 5}};
        int[] sort = cla.sort(5, arr1);
        System.out.println(Arrays.toString(sort));
    }

    // O0113 课程顺序
    private int[] sort(int total, Integer[][] arrs) {
        Map<Integer, List<Integer>> eleNeedMap = new HashMap<>();
        int[] inDegree = new int[total + 1];
        for (Integer[] arr : arrs) {
            if (arr[1] == null) {
                continue;
            }
            eleNeedMap.computeIfAbsent(arr[1], _ -> new LinkedList<>()).add(arr[0]);
            inDegree[arr[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] ans = new int[total];
        int idx = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans[idx++] = poll;
            for (Integer sub : eleNeedMap.getOrDefault(poll, new ArrayList<>())) {
                inDegree[sub]--;
                if (inDegree[sub] == 0) {
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
