package algorithm.lesson7;

import java.util.LinkedList;

/**
 * Класс инкапсулирует механизм обхода графа в ширину
 * */
public class BredthFirstPaths {

    private int edgeTo[];       //массив предшествующих вершин
    private boolean marked[];   //массив подтверждений посещения вершины
    private int distTo[];       //массив длины пути
    private int source;         //корневой узел
    private static final int INF = Integer.MAX_VALUE;   //значение по умолчанию для элементов массива distTo

    /**
     * Конструктор с параметрами
     * @param graph - граф для обхода
     * @param source - узел для старта
     * */
    public BredthFirstPaths(Graph graph, int source) {

        if(source < 0){
            throw new IllegalArgumentException();
        }
        if(source>=graph.vertexCount()){
            throw new IndexOutOfBoundsException();
        }
        this.source = source;
        edgeTo = new int[graph.vertexCount()];
        marked = new boolean[graph.vertexCount()];
        distTo = new int[graph.vertexCount()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = INF;
        }
        bfs(graph, source);
    }

    /**
     * Служебный метод реализующий обход в ширину.
     * @param graph - граф для обхода
     * @param source - узел для старта
     * */
    private void bfs(Graph graph, int source){

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        distTo[source] = 0;

        while (!queue.isEmpty()){
            int vertex = queue.removeFirst();
            for (int w:
                 graph.adjList(vertex)) {
                if(!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = vertex;
                    distTo[w] = distTo[vertex] + 1;
                    queue.addLast(w);
                }
            }
        }
    }

    /**
     * Метод подтверждающий: посещение вершины, наличие пути
     * @param dist - узел для анализа
     * */
    public boolean hasPathTo(int dist){

        if(dist < 0){
            throw new IllegalArgumentException();
        }
        if(dist>=marked.length){
            throw new IndexOutOfBoundsException();
        }
        return marked[dist];
    }

    /**
     * Метод возвращает список вершин определяющих кратчайший путь от корневого узла до заданного
     * @param dist - узел для анализа
     * */
    public LinkedList pathTo(int dist){

        if(!hasPathTo(dist)){
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        int vertex = dist;
        while (vertex!=source){
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        stack.push(source);
        return stack;
    }

    /**
     * Метод возвращает длину пути от корневого узла до заданного
     * @param dist - узел для анализа
     * */
    public int distTo(int dist){

        if(dist < 0){
            throw new IllegalArgumentException();
        }
        if(dist>=marked.length){
            throw new IndexOutOfBoundsException();
        }
        return distTo[dist];
    }
}
