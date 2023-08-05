package LB450;

// Find the minimum and maximum elements in the array.
public class Array2 {

    static int comparison;
    public static void main(String[] args) {

        int [] arr = {4,5,2,6,3,100, 9, 50, 140};
        // Brute force approach : 2(n-1)
        System.out.println(min(arr));
        System.out.println(max(arr));
        System.out.println("comparisons " + comparison);
        comparison = 0;
        // Divide & Conquer

        System.out.println(max2(arr, 0 , arr.length-1));
        System.out.println("comparisons " + comparison);
        comparison = 0;

        System.out.println(max3(arr));
        System.out.println("comparisons " + comparison);
        comparison = 0;
    }

    // Best Approach T = O(3n-1)/2  S = O(1)
    private static int max3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i += 2){
            ++comparison; // Loop will not run n times here so adding this line outside if else
            if(i == arr.length-1){
                return arr[i] > max ? arr[i] : max;
            }
            if(arr[i] > arr[i+1]){
                if(arr[i] > max){
                    max = arr[i];
                }
            }else {
                if(arr[i+1] > max){
                    max = arr[i+1];
                }
            }
        }
        return max;
    }

    // Divide & Conquer T = O(3n-1)/2  S = log(n) Comparisons: Tournament Method
    private static int max2(int[] arr, int l, int r) {
        if(l==r){
            ++comparison; // In recursion add this line in if else comparison
            return arr[l];
        }
        if(l+1==r){
            ++comparison;
            return arr[l] > arr[l+1] ? arr[l] : arr[l+1];
        }
        int mid = (r+l)/2;
        int max1 = max2(arr, l , mid);
        int max2 = max2(arr, mid+1 , r);


        return max1 > max2 ? max1 : max2;
    }



    private static int max(int[] arr) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            ++comparison;
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    private static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

}
