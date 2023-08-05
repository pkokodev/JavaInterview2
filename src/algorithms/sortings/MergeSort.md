## **Illustration:**

To know the functioning of merge sort, lets consider an array arr[] = {38, 27, 43, 3, 9, 82, 10}

- _At first, check if the left index of array is less than the right index, if yes then calculate its mid point_

![](RackMultipart20220906-1-qaobbf_html_cf497b4c0fd71cb9.jpg)

- _Now, as we already know that merge sort first divides the whole array iteratively into equal halves, unless the atomic values are achieved._
- _Here, we see that an array of 7 items is divided into two arrays of size 4 and 3 respectively._

![](RackMultipart20220906-1-qaobbf_html_d388729ca9c39dd3.jpg)

- _Now, again find that is left index is less than the right index for both arrays, if found yes, then again calculate mid points for both the arrays._

![](RackMultipart20220906-1-qaobbf_html_f7e6c06977ed1633.jpg)

- _Now, further divide these two arrays into further halves, until the atomic units of the array is reached and further division is not possible._

![](RackMultipart20220906-1-qaobbf_html_cacf0d43b65b037f.jpg)

- _After dividing the array into smallest units, start merging the elements again based on comparison of size of elements_
- _Firstly, compare the element for each list and then combine them into another list in a sorted manner._

![](RackMultipart20220906-1-qaobbf_html_a99656ff877f6ff6.jpg)

- _After the final merging, the list looks like this:_

![](RackMultipart20220906-1-qaobbf_html_71a9cd2e315f39ba.jpg)

The following diagram shows the complete merge sort process for an example array {38, 27, 43, 3, 9, 82, 10}.

If we take a closer look at the diagram, we can see that the array is recursively divided into two halves till the size becomes 1. Once the size becomes 1, the merge processes come into action and start merging arrays back till the complete array is merged.

![](RackMultipart20220906-1-qaobbf_html_f9270ee15a8368d6.png)

_Recursive steps of merge sort_

## **Algorithm:**

_step 1: start_

_step 2: declare array and left, right, mid variable_

_step 3: perform merge function._

_if left \> right_

_return_

_mid= (left+right)/2_

_mergesort(array, left, mid)_

_mergesort(array, mid+1, right)_

_merge(array, left, mid, right)_

_step 4: Stop_

Follow the steps below the solve the problem:

MergeSort(arr[], l, r)

If r \> l

- Find the middle point to divide the array into two halves:
    - middle m = l + (r – l)/2
- Call mergeSort for first half:
    - Call mergeSort(arr, l, m)
- Call mergeSort for second half:
    - Call mergeSort(arr, m + 1, r)
- Merge the two halves sorted in steps 2 and 3:
    - Call merge(arr, l, m, r)

**Time Complexity:** O(N log(N)), Sorting arrays on different machines. Merge Sort is a recursive algorithm and time complexity can be expressed as following recurrence relation.

_T(n) = 2T(n/2) + θ(n)_

The above recurrence can be solved either using the Recurrence Tree method or the Master method. It falls in case II of the Master Method and the solution of the recurrence is θ(Nlog(N)). The time complexity of Merge Sort isθ(Nlog(N)) in all 3 cases (worst, average, and best) as merge sort always divides the array into two halves and takes linear time to merge two halves.

**Auxiliary Space:** O(n), In merge sort all elements are copied into an auxiliary array. So N auxiliary space is required for merge sort.

## **Is Merge sort In Place?**

No, In merge sort the merging step requires extra space to store the elements.

## **Is Merge sort Stable?**

Yes, merge sort is stable.

## **Applications of Merge Sort:**

- [Merge Sort is useful for sorting linked lists in O(N log N) time](https://www.geeksforgeeks.org/merge-sort-for-linked-list/). In the case of linked lists, the case is different mainly due to the difference in memory allocation of arrays and linked lists. Unlike arrays, linked list nodes may not be adjacent in memory. Unlike an array, in the linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore, the merge operation of merge sort can be implemented without extra space for linked lists.
  In arrays, we can do random access as elements are contiguous in memory. Let us say we have an integer (4-byte) array A and let the address of A[0] be x then to access A[i], we can directly access the memory at (x + i\*4). Unlike arrays, we can not do random access in the linked list. Quick Sort requires a lot of this kind of access. In a linked list to access i'th index, we have to travel each and every node from the head to i'th node as we don't have a contiguous block of memory. Therefore, the overhead increases for quicksort. Merge sort accesses data sequentially and the need of random access is low.
- [Inversion Count Problem](https://www.geeksforgeeks.org/counting-inversions/)
- Used in [External Sorting](http://en.wikipedia.org/wiki/External_sorting)

## **Drawbacks of Merge Sort:**

- Slower compared to the other sort algorithms for smaller tasks.
- The merge sort algorithm requires an additional memory space of 0(n) for the temporary array.
- It goes through the whole process even if the array is sorted.
- [Recent Articles on Merge Sort](https://www.geeksforgeeks.org/tag/merge-sort/)
- [Coding practice for sorting.](https://practice.geeksforgeeks.org/tag-page.php?tag=sorting&isCmp=0)
- [Quiz on Merge Sort](https://www.geeksforgeeks.org/quiz-mergesort-gq/)