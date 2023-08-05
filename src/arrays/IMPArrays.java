package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IMPArrays {
    public static void main(String[] args) {

        //String elements = "1 25 42 6 13 8 9 34 56 90";
        //String elements2 = "1 2 3 4 5 6 7 8 9 10";
        //String elements3 = "1 2 2 1 5 6 1 5 1 5";

        //String elements4 = "2,2,1,1,1,1,2"; //Moore's Voting Algorithm T=O(n) S=O(1)
        //majorityElementOptimal(insertAll(elements4));

        //String elements5 = "-2,1,-3,4,-1,2,1,-5,4"; //Kadane's Algorithm | Largest Sum Contiguous Subarray
        //System.out.println(largestSumContiguousSubarray(insertAll(elements5)));

        //String elements6 = "7,1,5,3,6,4"; //Best Time to Buy and Sell Stock
        //System.out.println(buySellStock(insertAll(elements6)));


        String elements7 = "4,2,0,3,2,5"; // Trapping Rain Water
        System.out.println(trappingRainWater(insertAll(elements7)));


    }

    /*
    Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Solution:-
Water can be sored at point only if where height of left or right greater than current height point.
So we need to find out how much water can be stored at each index.Left biggest right biggest.

    */


    private static int trappingRainWater(int[] arr){
        int water = 0;
        //fill from left
        int[] leftBiggerWall = new int[arr.length];
        //fill from right
        int[] rightBiggerWall = new int[arr.length];

        leftBiggerWall[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            leftBiggerWall[i] = Math.max(arr[i], leftBiggerWall[i-1]);
        }

        rightBiggerWall[arr.length-1] = arr[arr.length-1];
        for (int i = arr.length-2; i >= 0; i--){
            rightBiggerWall[i] = Math.max(arr[i], rightBiggerWall[i+1]);
        }

        for (int i = 0; i < arr.length; i++){
            water += Math.min(leftBiggerWall[i], rightBiggerWall[i]) - arr[i];
        }
        return water;
    }


    /*
    Best Time to Buy and Sell Stock
    Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
    */
    private static int buySellStock(int[] arr) {
        int maxProfit = 0;
        int minSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] < minSoFar){
                minSoFar = arr[i];
            }
            if((arr[i] - minSoFar) > maxProfit){
                maxProfit = arr[i] - minSoFar;
            }
        }

        return maxProfit;
    }
    private static int buySellStockBruit(int[] arr) {
        int maxProfit = 0;
        int curProfit = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                curProfit = arr[j] - arr[i];
                if(curProfit > maxProfit){
                    maxProfit = curProfit;
                }else {
                    curProfit += arr[i];
                }
            }
        }

        return maxProfit;
    }

    /*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
A subarray is a contiguous part of an array.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
    */
    //Kadane's Algorithm | Largest Sum Contiguous Subarray
    private static int largestSumContiguousSubarray(int[] arr) {
        int maxSum = 0;
        int curSum = 0;
        for (int i = 0; i < arr.length; i++) {

            curSum += arr[i];
            if(curSum > maxSum) maxSum = curSum;
            if(curSum < 0) curSum = 0;
        }
        return maxSum;
    }


    /*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
    */

    //Moore's Voting Algorithm T=O(n) S=O(1)
    private static void majorityElementOptimal(int[] arr) {
        int major = -1;
        int frequency = 0;
        for (int i = 0; i < arr.length; i++) {

            if (frequency == 0) {
                major = arr[i];
            }
            if (major == arr[i]) {
                frequency++;
            } else {
                frequency--;
            }
        }
        System.out.println(major);
    }


    // Using Map Element frequency > N/2 T=O(n) S=O(n)
    private static void majorityElement(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(map.toString());
        map.forEach((k, v) -> {
            if (v > (arr.length / 2)) {
                System.out.println(k);
            }
        });
    }

    private static int[] insertAll(String elements) {

        int i = 0;
        String[] strArr = elements.split(" ");
        if (strArr.length == 1) {
            strArr = elements.split(",");
        }
        int[] arr = new int[strArr.length];
        for (String item :
                strArr) {
            arr[i++] = Integer.parseInt(item);
        }
        return arr;
    }
}
