package algorithms.sortings;

import java.util.Arrays;

public class SortingPractice {
    public static void main(String[] args) {
        int arr[] = new int[10];
        String elements = "1 25 42 6 13 8 9 34 56 90";
        String elements2 = "1 2 3 4 5 6 7 8 9 10";
        insertAll(arr, elements);
        System.out.println(Arrays.toString(arr));

        mererSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    static void mererSort(int[] arr, int start, int end){
        if(start < end){
            int mid = start + (end-start)/2;
            // Divide & Conquer means two nodes of BT
            mererSort(arr, start, mid);
            mererSort(arr, mid+1, end);

            //Backtracking -> going level up in BT
            merge(arr, start, end, mid);

        }
    }

    private static void merge(int[] arr, int start, int end, int mid) {
        // Find sizes of two sub-arrays to be merged
        int n1 = mid - start + 1;
        int n2 = end - mid;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second sub-arrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = start;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

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
