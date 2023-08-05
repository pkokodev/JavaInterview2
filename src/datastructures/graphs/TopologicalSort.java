package datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {

        //img_3.png
        Graph g = new Graph(6);
        g.addEdgeDirected(5, 2);
        g.addEdgeDirected(5, 0);
        g.addEdgeDirected(4, 0);
        g.addEdgeDirected(4, 1);
        g.addEdgeDirected(2, 3);
        g.addEdgeDirected(3, 1);

        topoSort1(g);
        topoSort2(g);

    }

    private static void topoSort2(Graph g) {
        boolean[] visited = new boolean[g.getVertices()];
        //Only dfs will not work for topological sort, hence need one more stack
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                dfs2(g.adjList, visited, stack, i);
            }
        }

        while (!stack.isEmpty()) System.out.println(stack.pop());
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, Stack<Integer> stack, int v) {
        visited[v] = true;

        for (Integer adjV: adjList.get(v)){
            if(!visited[adjV]){
                dfs2(adjList, visited, stack, adjV);
            }
        }
        stack.push(v);
    }

    private static void topoSort1(Graph g) {
        boolean[] visited = new boolean[g.getVertices()];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                dfs(g.adjList, visited, stack, i);
            }
        }

        int k = 0;
        int sorted[] = new int[stack.size()];
        while (!stack.isEmpty()){
            sorted[k++] = stack.pop();
        }

        System.out.println(Arrays.toString(sorted));
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adjList, boolean[] visited,
                                       Stack<Integer> stack, int source){
        visited[source] = true;
        for (Integer adjV: adjList.get(source)) {
            if(!visited[adjV]){
                visited[adjV] = true;
                dfs(adjList, visited, stack, adjV);
            }
        }
        //push when backtrack
        stack.push(source);
    }
}
