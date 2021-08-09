package com.icboluo.leetcode;

import com.icboluo.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author icboluo
 * @date 2021-05-09 13:05
 */
public class N0559_最大深度 {
    public int maxDepth(Node root) {
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
}
