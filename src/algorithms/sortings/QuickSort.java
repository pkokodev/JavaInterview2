package algorithms.sortings;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        //int[] arr = { 10, 7, 8, 9, 1, 5 };

        int q = partition(arr, 0, arr.length-1);
        System.out.println(q);
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int l, int r) {
        if(l < r) {
            int q = partition(arr, l, r);
            quickSort(arr, l, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l-1;
        for(int j = l; j < r; j++){
            if(arr[j] <= pivot){
                int temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[++i];
        arr[i] = pivot;
        arr[r] = temp;
        return i;
    }
}
