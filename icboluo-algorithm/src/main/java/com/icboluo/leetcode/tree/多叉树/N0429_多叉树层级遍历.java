package com.icboluo.leetcode.tree.多叉树;

import com.icboluo.common.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * @author icboluo
 * @since 2022-03-28 22:04
 */
class N0429_多叉树层级遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Node>> ans = new LinkedList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ans.add(new LinkedList<>(queue));
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                assert poll != null;
                if (poll.children != null && !poll.children.isEmpty()) {
                    queue.addAll(poll.children);
                }
            }
        }
        return ans.stream().map(li ->
                li.stream().map(node -> node.val).toList()
        ).toList();
    }
}
