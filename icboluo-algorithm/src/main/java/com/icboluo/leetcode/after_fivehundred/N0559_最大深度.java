package com.icboluo.leetcode.after_fivehundred;

import com.icboluo.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @since 2021-05-09 13:05
 */
 class N0559_最大深度 {
    public int maxDepth(Node root) {
        return maxDepth2(root);
    }

    public int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll == null) {
                    continue;
                }
                for (Node child : poll.children) {
                    queue.offer(child);
                }
            }
        }
        return depth;
    }

    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth2(child));
        }
        return max + 1;
    }
}
