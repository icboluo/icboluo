package com.icboluo.leetcode.onethousand;

import java.util.*;

/**
 * @author icboluo
 * @since 2022-07-10 13:34
 */
public class N1443_二叉树收集苹果最短时间 {
    Set<Integer> visited;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        visited = new HashSet<>();
        Map<Integer, List<Integer>> idxListMap = new HashMap<>();
        for (int[] edge : edges) {
            idxListMap.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            idxListMap.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }
        return dfs(idxListMap, hasApple, 0);
    }

    private int dfs(Map<Integer, List<Integer>> idxListMap, List<Boolean> hasApple, int cur) {
        // 此visited是为了防止反反复复访问
        visited.add(cur);
        int count = 0;
        for (Integer child : idxListMap.getOrDefault(cur, new ArrayList<>())) {
            if (visited.contains(child)) {
                continue;
            }
            count += dfs(idxListMap, hasApple, child);
        }
        boolean b = count > 0 || hasApple.get(cur);
        // 有苹果并且不是根节点
        if (b && cur != 0) {
            count += 2;
        }
        return count;
    }
}
