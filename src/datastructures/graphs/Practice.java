package datastructures.graphs;

import java.util.ArrayList;

public class Practice {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        boolean visited[] = new boolean[7];
        dfsRecursive(graph.adjList, visited, 1);
    }
    static void dfsRecursive(ArrayList<ArrayList<Integer>> adjList, boolean visited[], int source){
        visited[source] = true;
        System.out.println(source);
        for(int i = 0; i < adjList.get(source).size(); i++){
            int neighbour = adjList.get(source).get(i);
            if(!visited[neighbour]){
                dfsRecursive(adjList, visited, neighbour);
            }
        }
    }

}
