package datastructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
DFS BFS => T = O(V + E) Even though we  have for loop inside while loop.
Bcz total elements processed = All vertices + 2(All edges)

Need to maintain visited array for both to prevent falling in a loop.

Useful when we want to recursively travers in all direction at each position.
So in case of word search algorithm no need to use dfs, bcz once we choose the direction then we need to go straight
*/
public class Search {
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

        Search search = new Search();
        boolean visited[] = new boolean[7];
        //search.bfs(graph.adjList, visited, 1);
        //search.dfs(graph.adjList, visited, 1);

        /*//Number of components in the given graph
        int components = 0;
        for (int i = 0; i <= 6; i++){
            if(!visited[i]){
                components++;
                search.bfs(graph.adjList, visited, i);
            }
        }
        System.out.println("Components: " + components);*/



        /*// DFS using recursion: Callstack act as stack
        boolean visited2[] = new boolean[7];
        search.dfsRecursive(graph.adjList, visited2, 1);*/

        /*//Undirected graph : Detect cycle
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                if(search.isCycleDFS(graph.adjList, visited, i, -1)){
                    System.out.println(search.isCycleDFS(graph.adjList, visited, i, -1));
                    break;
                }
            }
        }*/

        //Directed graph : Detect cycle
        boolean visited2[] = new boolean[7];
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                if(search.isCycleDFS(graph.adjList, visited, i, -1)){
                    System.out.println(search.isCycleDirected(graph.adjList, visited, visited2, i, -1));
                    break;
                }
            }
        }
    }

    //need to maintain call stack to know whether neighbour in the call the stack or not
    boolean isCycleDirected(ArrayList<ArrayList<Integer>> adjList, boolean visited[], boolean recCall[], int source, int parent){
        visited[source] = true;
        recCall[source] = true;
        for(int i = 0; i < adjList.get(source).size(); i++){
            int neighbour = adjList.get(source).get(i);
            if(!visited[neighbour]){
                if(isCycleDFS(adjList, visited, neighbour, source)) return true;
            }else {
                if(recCall[neighbour]){
                    return true;
                }
            }
        }
        return false;
    }


    // Undirected graph:- need to maintain parent here
    //two conditions to detect cycle the node must
    //1. be visited 2. not be parent
    boolean isCycleDFS(ArrayList<ArrayList<Integer>> adjList, boolean visited[], int source, int parent){
        visited[source] = true;
        for(int i = 0; i < adjList.get(source).size(); i++){
            int neighbour = adjList.get(source).get(i);
            if(!visited[neighbour]){
                if(isCycleDFS(adjList, visited, neighbour, source)) return true;
            }else {
                if(neighbour != parent){
                    return true;
                }
            }
        }
        return false;
    }
    void dfsRecursive(ArrayList<ArrayList<Integer>> adjList, boolean visited[], int source){
        visited[source] = true;
        System.out.println(source);
        for(int i = 0; i < adjList.get(source).size(); i++){
            int neighbour = adjList.get(source).get(i);
            if(!visited[neighbour]){
                dfsRecursive(adjList, visited, neighbour);
            }
        }
    }

    void dfs(ArrayList<ArrayList<Integer>> adjList, boolean visited[], int source){

        Stack<Integer> stack = new Stack<>();
        visited[source] = true;
        stack.push(source);
        while (!stack.isEmpty()){
            int cur = stack.pop();
            System.out.print(cur + "-->");
            //Why for loop:- bcz we need fire recursive calls in all possible direction.
            // Binary tree has two direction left subtree and right subtree, so similarly graph can have any number of nodes i.e. size of adjList
            for(int i = 0; i < adjList.get(cur).size(); i++){
                int neighbour = adjList.get(cur).get(i);
                if(visited[neighbour] == false){
                    visited[neighbour] = true;
                    stack.push(neighbour);
                }
            }
        }
    }
    void bfs(ArrayList<ArrayList<Integer>> adjList, boolean visited[], int source){
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()){
            int cur = queue.poll();
            System.out.print(cur + "-->");
            for(int i = 0; i < adjList.get(cur).size(); i++){
                int neighbour = adjList.get(cur).get(i);
                if(visited[neighbour] == false){
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }
}
