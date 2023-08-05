package datastructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    public int getVertices() {
        return vertices;
    }

    private int vertices;
    // Adjacency list can be implemented as List of List or List of LinkedList or Array of LinkedList
     ArrayList<ArrayList<Integer>> adjList;
     int[][] adjMat;
    private ArrayList<LinkedList<Integer>> adjLinkedList;
    private LinkedList<Integer> adjListArr[];

    Graph(int v){
        vertices = v;
        adjList = new ArrayList<>();
        for (int i = 0; i < v; i++){
            adjList.add(new ArrayList<>());
        }
    }
    Graph(int v, boolean isMatrix){
        vertices = v;
        adjMat = new int[v][v];
    }

    void addEdge(int source, int dest){
        adjList.get(source).add(dest);
        adjList.get(dest).add(source);
    }
    void addEdgeDirected(int source, int dest){
        adjList.get(source).add(dest);
    }
    void addEdgeOnce(String str){
        String nodeStr[] = str.split(",");
        for (String node: nodeStr) {
            int source = Integer.parseInt(node.split(" ")[0]);
            int dest = Integer.parseInt(node.split(" ")[1]);
            adjList.get(source).add(dest);
            if(source != dest){
                adjList.get(dest).add(source);
            }

        }

    }
    void addEdgeOnceDirected(String str){
        String nodeStr[] = str.split(",");
        for (String node: nodeStr) {
            int source = Integer.parseInt(node.split(" ")[0]);
            int dest = Integer.parseInt(node.split(" ")[1]);
            adjList.get(source).add(dest);

        }

    }

    void addEdgeMatrix(int source, int dest){
        adjMat[source][dest] = 1;
        adjMat[dest][source] = 1;
    }
    void addEdgeMatrixDirected(int source, int dest){
        adjMat[source][dest] = 1;
    }
}
