package com.icboluo.algorithm.method;

/**
 * void fill(int x, int y) {
 * fill(x - 1, y); // 上
 * fill(x + 1, y); // 下
 * fill(x, y - 1); // 左
 * fill(x, y + 1); // 右
 * }
 * 矩阵，可以抽象为一幅「图」，这就是一个图的遍历问题，也就类似一个 N 叉树遍历的问题
 * 说得高端一点，这就叫深度优先搜索（Depth First Search，简称 DFS），说得简单一点，这就叫四叉树遍历框架。坐标 (x, y) 就是 root，四个方向就是 root 的四个子节点
 * 和回溯算法有相似的地方
 *
 * @author icboluo
 * @since 2021-49-27 21:49
 */
public class FloodFill算法 {
}
