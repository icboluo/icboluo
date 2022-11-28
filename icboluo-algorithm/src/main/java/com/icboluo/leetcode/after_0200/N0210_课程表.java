package com.icboluo.leetcode.after_0200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-11-26 15:27
 */
class N0210_课程表 {
    public static void main(String[] args) {
        N0210_课程表 cla = new N0210_课程表();
        int[][] arr = {{1, 0}};
        int[] order = cla.findOrder(2, arr);
        System.out.println(Arrays.toString(order));
    }

    private boolean[] visited;
    private boolean[] isPath;
    private List<Integer> ans;

    private boolean hasCycle;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }
        visited = new boolean[numCourses];
        isPath = new boolean[numCourses];
        ans = new ArrayList<>();
        // 这里用num来表示idx，graph表示ele更好一点；做之前一定要区分，是根据元素来处理还是idx来处理....循环这里属于易错点
        for (int i = 0; i < numCourses; i++) {
            // 这次每次更新针对于当前节点而言，求所有的子节点
            list(graph, i);
            if (hasCycle) {
                // 有环
                return new int[0];
            }
        }
        return ans.stream().sorted((a, b) -> -1).mapToInt(Integer::intValue).toArray();
    }

    private void list(LinkedList<Integer>[] graph, int start) {
        if (isPath[start]) {
            hasCycle = true;
            return;
        }
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        isPath[start] = true;
        for (Integer child : graph[start]) {
            list(graph, child);
        }
        // 后序遍历收集答案，最先出来的是上层依赖 1-2-3最后出来的是1...
        // 为什么可以后序遍历：1.访问一个节点的时候应该先访问未访问的子节点；
        // 2.后序遍历拥有子节点（需要被访问的节点）的所有信息
        ans.add(start);
        isPath[start] = false;
    }
}
