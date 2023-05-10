package com.icboluo.algorithm.floyd;

/**
 * 这个算法非常的简单，先构建一个距离图，然后将每个节点插进去，更新所有节点即可
 * 此块如果要追着路由，需要增加另一个二维数组，标记索引
 * 这个算法和FloodFill差距非常大
 *
 * @author icboluo
 * @since 2023-03-17 0:43
 */
class Floyd {
    /**
     * 不要Integer.max,两个相加越界为负
     */
    static int max = 6666666;

    public static void main(String[] args) {
        // 地图
        @SuppressWarnings("all") int[][] dist = {// -----------------
                {0, 2, 3, 6, max, max},// -----------------
                {2, 0, max, max, 4, 6},// -----------------
                {3, max, 0, 2, max, max},// -----------------
                {6, max, 2, 0, 1, 3},// -----------------
                {max, 4, max, 1, 0, max},// -----------------
                {max, 6, max, 3, max, 0}// -----------------
        };
        for (int k = 0; k < dist.length; k++) {
            // 松弛I行
            for (int i = 0; i < dist.length; i++) {
                // 松弛J列
                for (int j = 0; j < dist.length; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            System.out.print("节点 " + (i + 1) + " 的最短路径");
            for (int j = 0; j < dist.length; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}