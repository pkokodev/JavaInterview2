package algorithms.sortings;

import java.util.Arrays;

/*
Time Complexity: O(N^2)
Auxiliary Space: O(1)

What are the Boundary Cases of Insertion Sort algorithm?
Insertion sort takes maximum time to sort if elements are sorted in reverse order. And it takes minimum time (Order of n)
when elements are already sorted.

What are the Algorithmic Paradigm of Insertion Sort algorithm?
Insertion Sort algorithm follows incremental approach.

Is Insertion Sort an in-place sorting algorithm?
Yes, insertion sort is an in-place sorting algorithm.

Is Insertion Sort a stable algorithm?
Yes, insertion sort is a stable sorting algorithm.

When is the Insertion Sort algorithm used?
Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted,
only few elements are misplaced in complete big array.

What is Binary Insertion Sort?
We can use binary search to reduce the number of comparisons in normal insertion sort. Binary Insertion Sort uses binary search to find
the proper location to insert the selected item at each iteration. In normal insertion, sorting takes O(i) (at ith iteration) in worst case.
We can reduce it to O(logi) by using binary search. The algorithm, as a whole, still has a running worst case running time of O(n^2)
because of the series of swaps required for each insertion. Refer this for implementation.
*/
public class InsertionSort {
    public static void main(String[] args) {
       int arr[] = {6, 5,7, 0, 8, 2, 7, 1};
        System.out.println(Arrays.toString(insertionSort(arr)));
        System.out.println(Arrays.toString(insertionSort2(arr)));
    }

    private static int[] insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    private static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++){
            int key = arr[i];
            // At this point of key, left side array is already sorted.
            for (int j = i-1; j >= 0; j--){
                //If left element of key is less than key is at the right position so no need to check the right position of key.
                if(key >= arr[j] && j == i-1){
                    break;
                }
                if(key < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}

/*

First Pass:

Initially, the first two elements of the array are compared in insertion sort.
   12   	   11   	   13   	   5   	   6
Here, 12 is greater than 11 hence they are not in the ascending order and 12 is not at its correct position. Thus, swap 11 and 12.
So, for now 11 is stored in a sorted sub-array.
   11   	   12   	   13   	   5   	   6

Second Pass:

 Now, move to the next two elements and compare them
   11   	   12   	   13   	   5   	   6
Here, 13 is greater than 12, thus both elements seems to be in ascending order, hence, no swapping will occur.
12 also stored in a sorted sub-array along with 11

Third Pass:

Now, two elements are present in the sorted sub-array which are 11 and 12
Moving forward to the next two elements which are 13 and 5
   11   	   12   	   13   	   5   	   6
Both 5 and 13 are not present at their correct place so swap them
   11   	   12   	   5   	   13   	   6
After swapping, elements 12 and 5 are not sorted, thus swap again
   11   	   5   	   12   	   13   	   6
Here, again 11 and 5 are not sorted, hence swap again
   5   	   11   	   12   	   13   	   6
here, it is at its correct position

Fourth Pass:

Now, the elements which are present in the sorted sub-array are 5, 11 and 12
Moving to the next two elements 13 and 6
   5   	   11   	   12   	   13   	   6
Clearly, they are not sorted, thus perform swap between both
   5   	   11   	   12   	   6   	   13
Now, 6 is smaller than 12, hence, swap again
   5   	   11   	   6   	   12   	   13
Here, also swapping makes 11 and 6 unsorted hence, swap again
   5   	   6   	   11   	   12   	   13
Finally, the array is completely sorted.




*/