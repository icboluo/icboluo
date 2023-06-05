package com.icboluo.leetcode.after_0200;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author icboluo
 * @since 2023-06-05 23:22
 */
class N0378_有序矩阵中的第k小元素 {
    // 单行是升序的二维数组
    // 二分搜索是复杂的，可以采取优先级队列（最小堆）
    // 逐行分析，互不相干，整体的顺序有pq来决定，单行的顺序由本身来决定
    public int kthSmallest(int[][] matrix, int k) {
        // 存储的是坐标和值
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        // 将第一列的数据加入到队列中
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{i, 0, matrix[i][0]});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] poll = pq.poll();
            // 如果元素已经是行尾，算了
            assert poll != null;
            if (poll[1] == matrix[0].length - 1) {
                continue;
            }
            pq.offer(new int[]{poll[0], poll[1] + 1, matrix[poll[0]][poll[1] + 1]});
        }
        return Objects.requireNonNull(pq.poll())[2];
    }
}
