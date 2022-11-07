package com.icboluo.leetcode.after_0600;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-07 23:02
 */
class N0797_到目标的所有路径 {
    List<List<Integer>> ans;

    // TODO ERROR
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        dfs(graph, 0, list);
        return allPathsSourceTarget(graph);
    }

    private void dfs(int[][] graph, int start, LinkedList<Integer> track) {
        track.add(start);
        // 在叶子节点收集结果
        if (start == graph.length - 1) {
            ans.add(new LinkedList<>(track));
        }
        for (int child : graph[start]) {
            dfs(graph, child, track);
        }
        track.removeLast();
    }
}
