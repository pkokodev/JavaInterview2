package datastructures.graphs.algos;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        int graph[][] = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };
        int source = 0;
        boolean[] visited = new boolean[graph.length];
        int[] distance = new int[graph.length];
        for (int i = 0; i < distance.length; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        dijkstra(graph, visited, distance, source);
        System.out.println(Arrays.toString(distance));
    }

    private static void dijkstra(int[][] graph, boolean[] visited, int[] distance, int source) {
        for(int v = 0; v < graph.length; v++){
            int minV = findMin(distance, visited);
            visited[minV] = true;
            for (int col = 0; col < graph.length; col++){
                if(graph[minV][col] != 0 && !visited[col] && distance[minV] != Integer.MAX_VALUE){
                    int newDist = distance[minV] + graph[minV][col];
                    if(newDist < distance[col]){
                        distance[col] = newDist;
                    }
                }
            }
        }
    }

    // Using Heap can reduce the time complexity to log(n) but total T = E * log(V)
    private static int findMin(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int v = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && distance[i] < min){
                min = distance[i];
                v = i;
            }
        }

        return v;
    }
}
