package algorithms.searchings;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {10, 20, 30, 50, 60, 80, 110, 130, 140, 170};
        int index = binarySearch(arr, 0, arr.length-1, 110);
        System.out.println(index);
    }

    private static int binarySearch(int[] arr, int l, int r, int key) {
        int mid = -1;
        while (l <= r){
            mid = l + (r-l)/2;
            if(arr[mid] == key) break;
            if(key < arr[mid]){
                r = mid -1;
            }else {
                l = mid +1;
            }
        }
        return mid;
    }
}
