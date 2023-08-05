package swproblems;

import java.util.*;

/*
Sliding Window:- subarray or Consecutive elements

Problems like partition by in sql

Patterns:-
1. Array problems
2. Some aggregation operations in Subarray or consecutive. Usually takes O(n*k) but avoiding repeated calculations can be made to T = O(n).

Types:-
1. Fixed size :- Windows size is given, find aggregation among them. Ex. Find max sum in k consecutive elements in array.

2. Variable size :- Aggregation is given, find max or min size subarray. Ex. Find max subarray having sum 14 in array.
*/
public class SWPractice {
    public static void main(String[] args) {
        //Find max sum in k consecutive elements in array
        //int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        //int ans = maxSumInKSizeSubarray(arr, 4);
        //System.out.println(ans);

        //Find first negative integer in every window of size k
        //int[] arr2 = {12, -1, -7, 8, -15, 30, 16, 28};
        //firstNegativeInKSizeSubarrayMy(arr2, 3);

        //Count Occurences of Anagrams
        //String txt = "forxxorfxdofr";
        //String word = "for";
        //countAnagrams(txt, word);

        //Sliding Window Maximum (Maximum of all subarrays of size K)
        //int arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};//[3, 3, 4, 5, 5, 5, 6]
        //int arr[] = { 12, 1, 78, 90, 57, 89, 56 };//[12, 90, 90, 90, 89]
        //maxSW(arr, 3);

        //===============Variable Size Sliding Window=================

        //Longest sub-array having sum k
    }

    private static void maxSW(int[] arr, int k) {
        //arr = {1, 2, 3, 1, 4, 5, 2, 3, 6}; k = 3
        int[] ans = new int[arr.length - k + 1];
        Deque<Integer> arrDeck = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {

            if (!arrDeck.isEmpty() && arr[i] > arrDeck.getLast()) {
                arrDeck.removeLast();
            }
            arrDeck.offer(arr[i]);

        }
        ans[0] = arrDeck.peek();

        for (int i = k; i < arr.length; i++) {

            int out = arr[i - k];
            int in = arr[i];

            if (out == arrDeck.peek()) {
                arrDeck.poll();
            }


            while (!arrDeck.isEmpty() && in > arrDeck.getLast()) {
                arrDeck.removeLast();
            }

            arrDeck.offer(in);
            ans[i - k + 1] = arrDeck.peek();
        }

        System.out.println(Arrays.toString(ans));
    }

    /*
    //Count Occurences of Anagrams

    Input:
txt = forxxorfxdofr
pat = for
Output: 3
Explanation: for, orf and ofr appears
in the txt, hence answer is 3.
Solution:-

Anagrams:- possible anagrams _ _ _ -> 3 * 2 * 1

If anagram is present in string then elements should be contiguous. So window size  = 3
order of the character does not matter but only frequency does matter. So we are using extra space O(26)
    */
    private static void countAnagrams(String s, String p) {
        //txt = "forxxorfxdofr" , word = "for"
        int anagramCount = 0;
        HashMap<Character, Integer> sMap = new HashMap<>(26);
        HashMap<Character, Integer> pMap = new HashMap<>(26);

        int k = p.length();

        for (int i = 0; i < k; i++) {
            Character pChar = p.charAt(i);
            Character sChar = s.charAt(i);
            pMap.put(pChar, pMap.getOrDefault(pChar, 0) + 1);
            sMap.put(sChar, sMap.getOrDefault(sChar, 0) + 1);
        }
        if (pMap.equals(sMap)) {
            anagramCount++;
        }
        for (int i = k; i < s.length(); i++) {

            Character out = s.charAt(i - k);
            if (sMap.containsKey(out)) {
                sMap.put(out, sMap.get(out) - 1);

                //So that value can not go in minus
                if (sMap.get(out) == 0) {
                    sMap.remove(out);
                }
            }

            Character in = s.charAt(i);
            sMap.put(in, sMap.getOrDefault(in, 0) + 1);

            if (pMap.equals(sMap)) {
                anagramCount++;
            }

        }
        System.out.println(anagramCount);
    }

    /*

    Input :
N = 5
A[] = {-8, 2, 3, -6, 10}
K = 2
Output :
-8 0 -6 -6

Solution:-

Using queue to maintain useful negative numbers
    */
    private static void firstNegativeInKSizeSubarrayMy(int[] arr, int k) {
        int firstNegativeIndex = 0;
        int firstNegativeElement;

        for (int i = k - 1; i < arr.length; i++) {

            // Skip out of window and positive elements
            while ((firstNegativeIndex < i) &&
                    (firstNegativeIndex <= i - k ||
                            arr[firstNegativeIndex] >= 0)) {
                firstNegativeIndex++;
            }

            // Check if a negative element is
            // found, otherwise use 0
            if (arr[firstNegativeIndex] < 0) {
                firstNegativeElement = arr[firstNegativeIndex];
            } else {
                firstNegativeElement = 0;
            }
            System.out.print(firstNegativeElement + " ");
        }


    }
    private static void firstNegativeInKSizeSubarrayMyQueue(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        int k = 3;
        for (int i = 0; i < k; i++) {
            if(arr[i] < 0) queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if(!queue.isEmpty()){
                System.out.println(queue.peek());
            }else
            {
                System.out.println(0);
            }
            if(arr[i-k] == queue.peek()){
                queue.poll();
            }
            if(arr[i] < 0) queue.offer(arr[i]);
        }
        System.out.println(queue.peek() == null ? 0 : queue.poll() );

    }
    private static void firstNegativeInKSizeSubarray(int[] arr, int k) {
        // Process first k (or first window)
        // elements of array

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            // Add current element at the rear of Di
            // if it is a negative integer
            if (arr[i] < 0) {
                queue.offer(arr[i]);
            }
        }
        // Process rest of the elements,
        // i.e., from arr[k] to arr[n-1]
        for (int i = k; i < arr.length; i++) {
            // if Di is not empty then the element
            // at the front of the queue is the first
            // negative integer of the previous window
            if (!queue.isEmpty()) {
                System.out.println(queue.peek());
                // else the window does not have a
                // negative integer
            } else {
                System.out.println(0);
            }
            // Remove the elements which are
            // out of this window
            if ((!queue.isEmpty()) && queue.peek() == arr[i - k]) {
                queue.poll(); // Remove from front of queue
            }
            // Add current element at the rear of Di
            // if it is a negative integer
            if (arr[i] < 0) queue.offer(arr[i]);
        }
    }

    /*
     Given an array of integers of size ‘n’, Our aim is to calculate the maximum sum of ‘k’ consecutive elements in the array.

    Input  : arr[] = {100, 200, 300, 400}, k = 2
Output : 700

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}, k = 4
Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.

Input  : arr[] = {2, 3}, k = 3
Output : Invalid
There is no subarray of size 3 as size of whole array is 2.
    */
    private static int maxSumInKSizeSubarray(int[] arr, int k) {
        int maxSum = 0;

        //Brute Force
        for (int i = 0; i < (arr.length - k + 1); i++) {
            int windowSum = 0;
            for (int j = i; j < (i + k); j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(windowSum, maxSum);
        }

        //Optimal
        maxSum = 0;
        int windowSum = 0;
        for (int j = 0; j < k; j++) {
            windowSum += arr[j];
        }
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(windowSum, maxSum);
        }

        return maxSum;
    }
}
