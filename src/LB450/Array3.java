package LB450;

import java.util.*;

// Kth max element
public class Array3 {
    static int comparison;
    public static void main(String[] args) {
        int [] arr = {4,5,2,6,3,100, 9, 50, 140};

        System.out.println(kthMax(arr, 3));
        System.out.println("comparisons " + comparison);
        comparison = 0;
    }

    private static int kthMax(int[] arr, int k) {
        int max = 0;

        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        queue.addAll(list);

        for (int i = 1; i <= k; i++){
            max = queue.poll();
        }
        return max;
    }
}
