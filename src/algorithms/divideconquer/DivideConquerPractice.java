package algorithms.divideconquer;

import java.util.Arrays;

public class DivideConquerPractice {
    public static void main(String[] args) {
        int arr[] = new int[10];
        String elements = "1 25 42 6 13 8 9 34 56 90";
        String elements2 = "9 10 1 2 3 4 5 6 7 8";
        insertAll(arr, elements2);
        System.out.println(Arrays.toString(arr));

        System.out.println(binarySearchRotatedArr(arr, 7));

        System.out.println(Arrays.toString(arr));
    }

    // Which of the half is sorted -> at any point one part is always sorted so check in that part is element is lie there.
    private static int binarySearchRotatedArr(int[] arr, int target) {
        int start = 0;
        int end = arr.length-1;
        while (start <= end){
            int mid = start + (end -start)/2;
            if(arr[mid] == target) return mid;

            //left halft sorted
            if(arr[start] <= arr[end]){
                if(target >= arr[start] && target <= arr[mid])
                {
                    end = mid-1;
                }else{
                    start = mid+1;
                }

                //right halft is sorted
            }else {
                if(target >= arr[mid] && target <= arr[end]){
                    start = mid+1;
                }else {
                    end = mid -1;
                }
            }
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int start, int end, int item){
        int mid = start + (end-start)/2;
        if(arr[mid] == item){
            return mid;
        }
        if(item < arr[mid]){
            return binarySearch(arr, start, mid-1, item);
        }else {
            return binarySearch(arr, mid+1, end, item);
        }
    }


    private static int minSortedRotatedArr(int[] arr, int start, int end) {
        int mid = (start + end) / 2;
        if(start >= end){
            return arr[start];
        }
        // If decreasing means right part else left or if increasing left part else right part
        if(arr[mid] > arr[end]){
            return minSortedRotatedArr(arr, mid+1, end);
        }else {
            return minSortedRotatedArr(arr, start, mid);
        }


    }

    /*
    Let the array be arr[]={15, 18, 2, 3, 6, 12}
low = 0 , high = 5.
            =>  mid = 2
            =>  arr[mid]=2 , arr[mid-1] > arr[mid] , hence condition is matched
            =>  The required index = mid = 2

So the element is  found at index 2 and arr[2] = 2
    */
    private static int minSortedRotatedArrItr(int[] arr) {
        int start = 0;
        int end = arr.length-1;
        while (start < end){
            int mid = start + (end-start)/2;
            if(arr[mid] > arr[start]) start = mid+1;
            else end = mid;
        }
        return arr[start];
    }

    private static void insertAll(int[] arr, String elements) {
        int i = 0;
        for (String item:
                elements.split(" ")) {
            arr[i++] = Integer.parseInt(item);
        }
    }
}
