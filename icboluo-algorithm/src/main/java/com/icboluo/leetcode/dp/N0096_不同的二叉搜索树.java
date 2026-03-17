package com.icboluo.leetcode.dp;

/**
 * @author icboluo
 * @see com.icboluo.leetcode.分治.N0095_不同的二叉搜索树
 * @since 2024-04-25 21:45
 */
class N0096_不同的二叉搜索树 {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            // 对于i个元素，j：尝试每个节点作为根，累加左右子数的组合数（左*右）
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        var cla = new N0096_不同的二叉搜索树();
        assert cla.numTrees(3) == 5;
        assert cla.numTrees(1) == 1;
    }
}
