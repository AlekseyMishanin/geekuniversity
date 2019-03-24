package algorithm.lesson7;

public class Main {

    public static void main(String[] args) {

        Graph graph = new Graph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,4);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(4,5);
        graph.addEdge(2,6);
        graph.addEdge(2,7);
        graph.addEdge(3,7);
        graph.addEdge(3,9);
        graph.addEdge(6,5);
        graph.addEdge(6,8);
        graph.addEdge(6,9);

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph,0, false);

        BredthFirstPaths bredthFirstPaths = new BredthFirstPaths(graph,0);
        for (int i = 1; i < 10; i++) {
            System.out.println("Кратчайший путь к вершине " + i + ": " + bredthFirstPaths.pathTo(i));
        }
    }
}
