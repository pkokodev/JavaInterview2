package datastructures.graphs;

import java.util.LinkedList;
import java.util.Queue;

/*

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]

  --> 1

  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]

  --> 3

*/
public class Islands {
    static int[][] graph = new int[4][5];
    public static void main(String[] args) {
        addEdgeMatrixDirected(0,0);
        //addEdgeMatrixDirected(0,1);
        //addEdgeMatrixDirected(1,0);
        addEdgeMatrixDirected(1,1);
        addEdgeMatrixDirected(2,2);
        addEdgeMatrixDirected(3,3);
        addEdgeMatrixDirected(3,4);

        boolean[] visited = new boolean[graph[0].length];
        int components = 0;
        for(int i = 0; i < graph[0].length; i++){
            if(!visited[i]){
                components++;
                countIslands(graph, visited, i);
            }
        }

        System.out.println(components);

    }


    static void countIslands(int[][] adjMat, boolean[] visited, int source){
        visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()){
            queue.poll();
            for (int i = 0; i < adjMat[source].length; i++){
                int neighbour = adjMat[source][i];
                if(!visited[neighbour]){
                   visited[neighbour] = true;
                   queue.add(neighbour);
                }
            }

        }
    }

    private static void addEdgeMatrixDirected(int source, int dest) {
        graph[source][dest] = 1;
    }

}
