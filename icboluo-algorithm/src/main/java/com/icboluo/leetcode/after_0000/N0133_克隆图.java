package com.icboluo.leetcode.after_0000;

import com.icboluo.algorithm.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icboluo
 * @since 2022-11-03 21:11
 */
 class N0133_克隆图 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node copy = new Node(node.val);
        Map<Integer, Node> visitedMap = new HashMap<>();
        dfs(visitedMap, node, copy);
        return copy;
    }

    private void dfs(Map<Integer, Node> visitedMap, Node node, Node copy) {
        visitedMap.put(copy.val, copy);
        // 一个原有节点必须增加到现有图中（for循环内必须执行add函数；dfs只是针对于没有被访问过的节点）
        for (Node neighbor : node.neighbors) {
            if (visitedMap.containsKey(neighbor.val)) {
                copy.neighbors.add(visitedMap.get(neighbor.val));
            }else{
                Node temp = new Node(neighbor.val);
                copy.neighbors.add(temp);
                dfs(visitedMap, neighbor, temp);
            }
        }
    }
}
