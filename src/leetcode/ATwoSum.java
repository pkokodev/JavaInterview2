package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Leetcode #1
public class ATwoSum {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target =  7;
        System.out.println(Arrays.toString(twoSum( nums, target)));
        System.out.println(Arrays.toString(twoSumHashMap( nums, target)));
    }


    //BruteForce O(n^2)
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length -2; i++){
            for (int j = i+1; j < nums.length-1; j++){
                if(nums[i] + nums[j] == target){

                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }

    // O(n) using hashmap
    private static int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {0, 0};
    }
}
