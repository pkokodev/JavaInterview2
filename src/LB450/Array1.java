package LB450;

import java.util.Arrays;

// Write a program to reverse an array or string
public class Array1 {
    public static void main(String[] args) {
        System.out.println(reverseWord("sachin"));
        System.out.println(Arrays.toString(reverseNumbers(new int[] {5, 4, 3, 2, 1, 0})));
    }

    public static String reverseWord(String str)
    {
        char [] arr = str.toCharArray();//O(n)
        for(int i = 0, j = str.length() - 1; i < j; i++, j--){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

        }

        return String.valueOf(arr);
    }

    public static int [] reverseNumbers(int [] arr)
    {
        for(int i = 0, j = arr.length - 1; i < j; i++, j--){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

        }

        return arr;
    }
}
