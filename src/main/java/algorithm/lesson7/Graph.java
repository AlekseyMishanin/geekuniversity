package algorithm.lesson7;

import java.util.LinkedList;

public class Graph {

    private int vCount; //кол-во вершин
    private int eCount; //кол-во ребер
    private LinkedList<Integer>[] adjList;

    public Graph(int vCount) {

        if(vCount<0){
            throw new IllegalArgumentException();
        }
        this.vCount = vCount;
        this.adjList = new LinkedList[vCount];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public int vertexCount(){
        return vCount;
    }

    public int edgeCount(){
        return eCount;
    }

    public void addEdge(int a1, int a2){

        if(a1 < 0 || a2 < 0){
            throw new IllegalArgumentException();
        }
        if(a1 >= vCount || a2 >= vCount){
            throw new IndexOutOfBoundsException();
        }
        adjList[a1].add(a2);
        adjList[a2].add(a1);
        eCount++;
    }

    public LinkedList<Integer> adjList(int vertex){
        return adjList[vertex];
    }
}
