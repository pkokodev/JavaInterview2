package algorithms.heaps;

import java.util.*;

// Maxheap -> nlogn K-sized && MinHeap -> klogk
// Stream of numbers :-> Numbers will keep on adding so Using K-Sized MinHeap is optimal solution
// Top K largest elements:-> poll k-sized map till empty
public class KthLargest {
    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(20, 10, 30, 5, 50, 40));
        buildHeap(list);
        System.out.println(list);
        kthLargest(list, 3);

        System.out.println("----------Using Priority Queue------------");
        list = new ArrayList<>();
        list.addAll(Arrays.asList(20, 10, 30, 5, 50, 40));
        kthLargestPQ(list, 3);

        System.out.println("----------Using K-Sized MinHeap------------");
        list = new ArrayList<>();
        list.addAll(Arrays.asList(20, 10, 30, 5, 50, 40));
        kthLargestMinHeap(list, 3);
*/
        System.out.println("Top K frequent elements in given array");
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(20, 10, 30, 5,5,5,5, 50, 40, 10, 50, 10, 20, 30, 10, 50, 10, 20, 50, 10, 20, 20, 30, 10, 50, 10, 20));
        topKFreqElements(list, 3);
    }


    //Top K frequent elements in given array
    private static void topKFreqElements(List<Integer> list, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer item: list) {
            map.put(item, map.getOrDefault(item, 0) +1);
            /*if(map.containsKey(item)){
                map.put(item, map.get(item)+1);
            }else {
                map.put(item, 1);
            }*/
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((n1, n2)-> n1.getValue() - n2.getValue());
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(i < k){
                queue.add(entry);

            }
            if(entry.getValue() > queue.peek().getValue()){
                queue.poll();
                queue.add(entry);
            }
            i++;
        }
        System.out.println(queue);

    }

    private static void kthLargestMinHeap(List<Integer> list, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++){
            queue.add(list.get(i));
        }
        // So now root is the kth largest(not largest) in k-sized minHeap
        for (int i = k; i < list.size(); i++){
            if(list.get(i) > queue.peek()){
                queue.poll();
                queue.add(list.get(i));
            }
        }
        System.out.println(queue.peek());
    }

    private static void kthLargestPQ(List<Integer> list, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.addAll(list);
        while (k > 0){
            System.out.println(queue.poll());
            k--;
        }
    }

    private static void kthLargest(List<Integer> list, int k) {
        while (k > 0){
            System.out.println(extractMax(list));
            k--;
        }
    }

    private static int extractMax(List<Integer> list){
        int max = list.get(0);
        list.set(0, list.get(list.size() -1));
        list.remove(list.size()-1);
        maxHeapify(list, 0);
        return max;
    }
    private static void buildHeap(List<Integer> list) {
        int nonLeafNode = list.size()/2 - 1;
        int i = nonLeafNode;
        while(i >= 0 ){
            maxHeapify(list, i);
            i--;
        }
    }

    private static void maxHeapify(List<Integer> list, int i) {
        int lc = i*2 + 1;
        int rc = i*2 + 2;
        int largest = i;
        if(lc < list.size() && list.get(lc) > list.get(largest)){
            largest = lc;
        }
        if(rc < list.size() && list.get(rc) > list.get(largest)){
            largest = rc;
        }
        if(largest != i){
            int temp = list.get(largest);
            list.set(largest, list.get(i));
            list.set(i, temp);
            maxHeapify(list, largest);
        }
    }
}
