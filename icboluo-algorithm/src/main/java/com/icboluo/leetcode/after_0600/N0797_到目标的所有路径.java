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

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        track.add(0);
        dfs(graph, 0, track);
        return ans;
    }

    private void dfs(int[][] graph, int start, LinkedList<Integer> track) {
        // 在叶子节点收集结果
        if (start == graph.length - 1) {
            ans.add(new LinkedList<>(track));
        }
        for (int child : graph[start]) {
            // 回溯的位置越近越好，尽可能的近；如果removeLast放在最下面，会造成整个for循环完才回溯一次，这是不合理的
            track.add(child);
            dfs(graph, child, track);
            track.removeLast();
        }
    }
}
