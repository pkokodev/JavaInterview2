package arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyPractice {
    public static void main(String[] args) {
        /*
          Input :
N = 5
A[] = {-8, 2, 3, -6, 10}
K = 2
Output :
-8 0 -6 -6

        */
        int[] arr1 = {12, -1, -7, 8, -15, 30, 16, 28};
        cal(arr1);
    }

    private static void cal(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            if(arr[i] < 0) queue.offer(arr[i]);
        }
        for (int i = 3; i < arr.length; i++) {
            if(!queue.isEmpty()){
                System.out.println(queue.peek());
            }else
            {
                System.out.println(0);
            }
            if(arr[i-3] == queue.peek()){
                queue.poll();
            }
            if(arr[i] < 0) queue.offer(arr[i]);
        }
        System.out.println(queue.peek() == null ? 0 : queue.poll() );

    }

}
