package com.icboluo.leetcode.graph;

import com.icboluo.common.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 拓扑排序
 * @author icboluo
 * @since 2022-11-26 14:30
 */
class N0207_课程表 {
    public static void main(String[] args) {
        var cla = new N0207_课程表();
        System.out.println(cla.canFinish1(2, new int[][]{{1, 0}}));
        System.out.println(cla.canFinish2(2, new int[][]{{1, 0}}));
        System.out.println(cla.canFinish1(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(cla.canFinish2(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(cla.canFinish1(4, new int[][]{{2, 0}, {1, 0}, /*{3, 1},*/ {3, 2}, {1, 3}}));
        System.out.println(cla.canFinish2(4, new int[][]{{2, 0}, {1, 0}, /*{3, 1},*/ {3, 2}, {1, 3}}));
    }

    /**
     * 全局是否被访问
     */
    boolean[] visited;
    /**
     * 当前路径是否被访问
     */
    boolean[] isPath;

    /**
     * 是否有环
     */
    boolean hasCycle;

    public boolean canFinish1(int num, int[][] prerequisites) {
        visited = new boolean[num];
        isPath = new boolean[num];
        hasCycle = false;

        // parent: 1, sub: 2 3 我们可以认为，做2之前需要做1，做3之前需要做1
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        for (int[] item : prerequisites) {
            graph.computeIfAbsent(item[1], _ -> new LinkedList<>()).add(item[0]);
        }

        // 这里采取的是随机遍历，任意课程都可以访问通过
        for (int i = 0; i < num; i++) {
            list(graph, i);
        }
        // 无环代表可以被全部访问
        return !hasCycle;
    }

    private void list(Map<Integer, LinkedList<Integer>> graph, int start) {
        if (isPath[start]) {
            hasCycle = true;
            return;
        }
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        isPath[start] = true;
        for (Integer child : graph.getOrDefault(start, new LinkedList<>())) {
            list(graph, child);
        }
        isPath[start] = false;
    }

    /**
     * opt 入度出度
     *
     * @param num
     * @param arr arr[1]->arr[0]
     * @return
     */
    public boolean canFinish2(int num, int[][] arr) {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        for (int[] item : arr) {
            graph.computeIfAbsent(item[1], _ -> new LinkedList<>()).add(item[0]);
        }
        // 入度：下层，子节点
        int[] inDegree = new int[num];
        for (int[] item : arr) {
            inDegree[item[0]]++;
        }
        // 找出入度为0的元素
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        int[] ans = new int[num];
        // BFS 所有元素以及下一层相邻元素，这里采取的是顺序遍历
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans[count++] = poll;
            for (Integer sub : graph.getOrDefault(poll, new LinkedList<>())) {
                inDegree[sub]--;
                if (inDegree[sub] == 0) {
                    queue.add(sub);
                }
            }
        }
        return count == num;
    }

    // DFS 算法：关注点在节点
    private void traverse1(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("进入节点" + root);
        for (Node child : root.children) {
            traverse1(child);
        }
        System.out.println("离开节点" + root);
    }

    // 回溯算法，关注点在树枝
    private void traverse2(Node root) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            System.out.printf("从 %s 到 %s", root, child);
            traverse2(child);
            System.out.printf("从 %s 到 %s", child, root);
        }
    }
}
