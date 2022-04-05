package com.icboluo.datastructure.graph;

/**
 * @author icboluo
 * @since 2020-07-31 12:26
 */
class Demo {
    public static void main(String[] args) {
        int n = 5;
        String[] vertexs = {"a", "b", "c", "d", "e"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();

        //graph.dfs();
        graph.bfs();
    }
}
