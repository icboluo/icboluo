package com.icboluo.algorithm.prim;

/**
 * @author icboluo
 * @since 2020-08-05 17:20
 */
class MGraph {
    /**
     * 图节点个数
     */
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
