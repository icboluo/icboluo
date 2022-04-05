package com.icboluo.algorithm.prim;

/**
 * @author icboluo
 * @since 2020-08-05 17:20
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int verxs = data.length;
        int[][] weight = new int[][]{};
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.creatGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);
    }
}
