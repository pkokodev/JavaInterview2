package algorithms.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
Minimum Cost of ropes

There are given N ropes of different lengths, we need to connect these ropes into one rope.
The cost to connect two ropes is equal to sum of their lengths. The task is to connect the ropes
with minimum cost.

Example 1:

Input:
n = 4
arr[] = {4, 3, 2, 6}
Output:
29
*/
public class MinCost {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 7, 6, 9));//62

        buildHeap(list);
        //System.out.println(minCost(list));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.addAll(list);
        System.out.println(minCostPQ(queue));

    }

    private static int minCostPQ(PriorityQueue<Integer> queue) {
        if(queue.size() == 2){
            return queue.poll() + queue.poll();
        }
        int cost = queue.poll() + queue.poll();
        queue.add(cost);
        return cost + minCostPQ(queue);
    }


    private static int minCost(List<Integer> list) {
        if(list.size() == 2){
            return list.get(0) + list.get(1);
        }
        int min1 = extractMin(list);
        int min2 = extractMin(list);
        int cost = min1 + min2;
        insertKey(list, cost);
        return cost + minCost(list);
    }

    private static void insertKey(List<Integer> list, int key) {
        list.add(key);
        int i = list.size() -1;
        while (i > 0 && list.get(i) < list.get((i-1)/2)){
            int temp = list.get(i);
            list.set(i, list.get((i-1)/2));
            list.set((i-1)/2, temp);
            i = (i-1)/2;
        }
    }
    private static int extractMin(List<Integer> list){
        int max = list.get(0);
        list.set(0, list.get(list.size() -1));
        list.remove(list.size()-1);
        minHeapify(list, 0);
        return max;
    }
    private static void buildHeap(List<Integer> list) {
        int nonLeafNode = list.size()/2 - 1;
        int i = nonLeafNode;
        while(i >= 0 ){
            minHeapify(list, i);
            i--;
        }
    }

    private static void minHeapify(List<Integer> list, int i) {
        int lc = i*2 + 1;
        int rc = i*2 + 2;
        int largest = i;
        if(lc < list.size() && list.get(lc) < list.get(largest)){
            largest = lc;
        }
        if(rc < list.size() && list.get(rc) < list.get(largest)){
            largest = rc;
        }
        if(largest != i){
            int temp = list.get(largest);
            list.set(largest, list.get(i));
            list.set(i, temp);
            minHeapify(list, largest);
        }
    }
}
