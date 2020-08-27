package com.icboluo.datastructure.algorithm.dijkstra;

/**
 * @author icboluo
 * @date 2020-08-07 12:32
 */
class Demo {
    public static void main(String[] args) {
        char[] vertex = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = Integer.MAX_VALUE;
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showdjs();
    }
}
