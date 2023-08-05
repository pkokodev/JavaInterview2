package arrays;

import java.util.Arrays;

public class ArrayPractice {
    public static void main(String[] args) {
        int arr[] = new int[10];
        String elements = "1 25 42 6 13 8 9 34 56 90";
        String elements2 = "1 2 3 4 5 6 7 8 9 10";
        insertAll(arr, elements);
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    static void insertionSort(int[] arr){
        for(int i = 1, j = 0; i < arr.length; i++){
            int key = arr[i];
            while (j >= 0 && key < arr[j]){
                    arr[j+1] = arr[j];
                    j--;
            }
            arr[j+1] = key;
            j = i;
        }
    }
    static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
        for(int j = 0; j < arr.length - 1; j++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        }
    }

    private static int binarySearch(int[] arr, int start, int end,  int item) {
        if(start > end){
            return -1;
        }
        int mid = (start + end) / 2;
        if(arr[mid] == item){
            return mid;
        }

        if(item < arr[mid]){
            return binarySearch(arr,start, mid-1, item);
        }else {
            return binarySearch(arr,mid+1, end, item);
        }
    }

    private static int binarySearchItr(int[] arr, int start, int end,  int item) {

        while (start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == item){
                return mid;
            }

            if(item < arr[mid]){
                end = mid-1;
            }else {
               start = mid+1;
            }

        }

        return -1;
    }

    private static void insertAll(int[] arr, String elements) {
        int i = 0;
        for (String item:
             elements.split(" ")) {
            arr[i++] = Integer.parseInt(item);
        }
    }
}
