package algorithm.lesson7;

import java.util.LinkedList;

/**
 * Класс инкапсулирует механизм обхода графа в глубину
 * */
public class DepthFirstPaths {

    private boolean[] marked;   //массив подтверждений посещения вершины
    private int[] edgeTo;       //массив предшествующих вершин
    private int source;         //корневой узел

    /**
     * Конструктор с параметрами
     * @param graph - граф для обхода
     * @param source - узел для старта
     * @param flag - признак обхода графа в глубину через рекурсию или цикл (true - рекурсия, false - цикл
     * */
    public DepthFirstPaths(Graph graph, int source, boolean flag){

        if(source < 0){
            throw new IllegalArgumentException();
        }
        if(source>=graph.vertexCount()){
            throw new IndexOutOfBoundsException();
        }
        this.source = source;
        edgeTo = new int[graph.vertexCount()];
        marked = new boolean[graph.vertexCount()];
        if(flag) {
            dfs(graph, source);
        } else {
            dfs(graph);
        }
    }

    /**
     * Служебный метод инкапсулирующий обход графа в глубину при помощи рекурсии
     * @param g - граф для обхода
     * @param v - узел для старта
     * */
    private void dfs(Graph g, int v){
        
        marked[v] = true;
        for (int w:
             g.adjList(v)) {
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    /**
     * Служебный метод инкапсулирующий обход графа в глубину при помощи цикла
     * @param g - граф для обхода
     * */
    private void dfs(Graph g){

        LinkedList<Integer> stack = new LinkedList<>();
        marked[source] = true;
        stack.addLast(source);
        while(!stack.isEmpty()){
            int childVertex = -1;
            for (int w:
                    g.adjList(stack.peek())) {
                if(!marked[w]){
                    edgeTo[w] = stack.peek();
                    childVertex = w;
                    break;
                }
            }
            if(childVertex==-1){
                stack.pop();
            } else {
                marked[childVertex] = true;
                stack.push(childVertex);
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

        return stack;
    }
}
