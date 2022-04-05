package com.icboluo.algorithm.floyd;

/**
 * @author icboluo
 * @since 2020-08-10 12:57
 */
class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'f'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = Integer.MAX_VALUE;
        matrix[0] = new int[]{};

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
    }


}
