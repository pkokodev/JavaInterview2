package algorithms.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(20, 10, 30, 5, 50, 40));
        buildHeap(list);
        System.out.println(list);
        heapSort(list);
        System.out.println(list);
        /*insertKey(list, 60);
        System.out.println(list);*/
        /*increaseKey(list, list.size() -1, 300);
        System.out.println(list);*/
        /*list.addAll(Arrays.asList(50, 30, 40, 10, 5, 20));

        insert(list, 60);
        System.out.println(list);

        deleteMax(list);
        System.out.println(list);*/
        /*list.addAll(Arrays.asList(50, 30, 40, 10, 5, 20));

        insert(list, 60);
        System.out.println(list);

        deleteMax(list);
        System.out.println(list);*/
    }

    private static void heapSort(List<Integer> list) {
        int heapSize = list.size();
        while (heapSize > 0){
            int temp = list.get(0);
            list.set(0, list.get(heapSize-1));
            list.set(heapSize-1, temp);
            heapSize--;
            maxHeapify(list, 0 , heapSize);
        }

    }
    private static void maxHeapify(List<Integer> list, int i, int heapSize) {
        int lc = i * 2 + 1;
        int rc = i * 2 + 2;
        int largest = i;
        if(lc < heapSize && list.get(lc) > list.get(largest)){
            largest = lc;
        }
        if(rc < heapSize && list.get(rc) > list.get(largest)){
            largest = rc;
        }
        if(largest != i){
            int temp = list.get(largest);
            list.set(largest, list.get(i));
            list.set(i, temp);
            maxHeapify(list, largest, heapSize);
        }
    }

    private static void insertKey(List<Integer> list, int key) {
        list.add(key);
        int i = list.size() -1;
        while (i > 0 && list.get((i-1)/2) < list.get(i)){
            int temp = list.get(i);
            list.set(i, list.get((i-1)/2));
            list.set((i-1)/2, temp);
            i = (i-1)/2;
        }
    }

    private static void increaseKey(List<Integer> list, int i, int key) {
        if(key < list.get(i)){
            System.out.println("Unsupported operation");
            return;
        }
        list.set(i, key);
        while (i > 0 && list.get((i-1)/2) < list.get(i)){
            int temp = list.get(i);
            list.set(i, list.get((i-1)/2));
            list.set((i-1)/2, temp);
            i = (i-1)/2;
        }
    }

    private static void buildHeap(List<Integer> list){
        int lastNonLeaf = list.size()/2 - 1;
        int i = lastNonLeaf;
        while (i >= 0){
            maxHeapify(list, i);
            i--;
        }
    }

    private static void maxHeapify(List<Integer> list, int i) {
        int lc = i * 2 + 1;
        int rc = i * 2 + 2;
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

    private static void maxHeapifyIterative(List<Integer> list, int index){
        int i = index;
        int lastNonLeaf = list.size()/2 -1;
        while(i <= lastNonLeaf){
            int lc = (i << 1) + 1;
            int rc = (i << 1) + 2;
            int largest = index;
            if(rc >= list.size() && list.get(lc) > list.get(index)) {
                largest = lc;
            }else {
                largest = list.get(lc) > list.get(rc) ? (i << 1) + 1 : (i << 1) + 2;
            }

            if(list.get(i) < list.get(largest)){
                int temp = list.get(largest);
                list.set(largest, list.get(i));
                list.set(i, temp);
                i = largest;
            }else {
                return;
            }

        }
    }
    private static void deleteMax(List<Integer> list) {
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        maxHeapify(list, 0);
    }
    private static void deleteMax2(List<Integer> list) {
        list.set(0, list.get(list.size() -1));
        list.remove(list.size()-1);
        int i = 0;
        int lastNonLeaf = (int) (Math.floor(list.size()/2.0) -1);
        while(i <= lastNonLeaf && (i << 1) + 2 < list.size()){
            int lc = (i << 1) + 1;
            int rc = (i << 1) + 2;
            int largest = list.get(lc) > list.get(rc) ? (i << 1) + 1 : (i << 1) + 2;
            if(list.get(i) < list.get(largest)){
                int temp = list.get(largest);
                list.set(largest, list.get(i));
                list.set(i, temp);
                i = largest;
            }else {
                return;
            }

        }

    }
    private static void insert(List<Integer> list, int item) {
        list.add(60);
        int i = list.size() -1;
        while(i > 0){
            int parent = (int) (Math.ceil(i/2.0) -1);
            if(list.get(i) > list.get(parent)){
                int temp = list.get(parent);
                list.set(parent, list.get(i));
                list.set(i, temp);
                i = parent;
            }
        }

    }

}
