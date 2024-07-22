package com.icboluo.leetcode.前缀和_差分数组_联合查找;

/**
 * 并查集, 连通分量, DisjointSetUnion（不交集的并集）
 *
 * @author icboluo
 * @since 2024-07-18 下午10:34
 */
public class UnionFind {

    private final int[] parent;

    private final int[] rank;
    int count;


    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 查找根节点
     *
     * @param x
     * @return
     */
    public int find(int x) {
        // 如果元素x的父节点不是自己，那么将x的父节点设置为x的父节点的父节点。这是路径压缩的过程，可以减少查找时间
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 联合
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        count--;
        // 基于size进行优化，将小树安到大树上
        // 基于rank进行优化，将矮树安到高树上（目前是这种方式
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // 因为2个节点的层数相同，被合并的数必然层数会+1
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
