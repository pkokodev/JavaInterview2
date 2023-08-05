package datastructures.graphs;

import java.util.ArrayList;

public class DetectCycle {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdgeOnceDirected("4 4,0 1,1 2,2 3,3 3");
        //graph.addEdgeOnceDirected("0 1,1 2");

        boolean visited[] = new boolean[graph.getVertices()];
        boolean recCall[] = new boolean[graph.getVertices()];

        // Detect cycle in directed graph
        for(int i = 0; i < graph.getVertices(); i++){
            if(isCycleDirected(graph.adjList, visited, recCall, i, -1)){
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }

    static boolean isCycleDirected(ArrayList<ArrayList<Integer>> adjList, boolean visited[], boolean recCall[], int source, int parent){
        visited[source] = true;
        recCall[source] = true;
        for(int i = 0; i < adjList.get(source).size(); i++){
            int neighbour = adjList.get(source).get(i);
            if(!visited[neighbour]){
                if(isCycleDirected(adjList, visited, recCall, neighbour, source)) return true;
            }else {
                if(recCall[neighbour]){
                    return true;
                }
            }
        }
        recCall[source] = false;
        return false;
    }
}
