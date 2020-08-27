package com.icboluo.datastructure.algorithm.kruskal;

/**
 * @author icboluo
 * @date 2020-08-06 00:57
 */
class Demo {
    public static void main(String[] args) {
        char[] vertexs = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int matrix[][] = {};
        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();
    }
}
