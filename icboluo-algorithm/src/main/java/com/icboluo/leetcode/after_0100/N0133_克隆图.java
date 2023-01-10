package com.icboluo.leetcode.after_0100;

import com.icboluo.common.GraphNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-03 21:11
 */
class N0133_克隆图 {
    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) {
            return null;
        }
        GraphNode copy = new GraphNode(node.val);
        Map<Integer, GraphNode> visitedMap = new HashMap<>();
        dfs(visitedMap, node, copy);
        return copy;
    }

    private void dfs(Map<Integer, GraphNode> visitedMap, GraphNode node, GraphNode copy) {
        visitedMap.put(copy.val, copy);
        // 一个原有节点必须增加到现有图中（for循环内必须执行add函数；dfs只是针对于没有被访问过的节点）
        for (GraphNode neighbor : node.neighbors) {
            if (visitedMap.containsKey(neighbor.val)) {
                copy.neighbors.add(visitedMap.get(neighbor.val));
            } else {
                GraphNode temp = new GraphNode(neighbor.val);
                copy.neighbors.add(temp);
                dfs(visitedMap, neighbor, temp);
            }
        }
    }
}
