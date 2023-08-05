package datastructures.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Problems {
    public static void main(String[] args) {
        int[] arr = {4, 10, 5, 8, 20, 15, 3, 12};
       //Nearest Smaller Element on Left & Right side of an Array
        //nearestSmaller(arr);

        //Valid Parenthesis Checker
        //isValidParenthesis("([{}])");

        //Largest Rectangular Area in a Histogram
        //int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
        //System.out.println(largestAreaHistogram(hist));//12

        //
        int A[][] = {
                { 0, 1, 1, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 1, 1, 0, 0 },
        };
        System.out.println(maxRectangle(4, 4, A));
    }

    /*
    Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.

Example:

Input:
0 1 1 0
1 1 1 1
1 1 1 1
1 1 0 0
Output :
8
Explanation :
The largest rectangle with only 1's is from
(1, 0) to (2, 3) which is
1 1 1 1
1 1 1 1

Input:
0 1 1
1 1 1
0 1 1
Output:
6
Explanation :
The largest rectangle with only 1's is from
(0, 1) to (2, 2) which is
1 1
1 1
1 1

Solution:- start row by row and consider this row as largestAreaHistogram problem
    */
    private static int maxRectangle(int R, int C, int[][] A) {
        // Calculate area for first row and initialize it as
        // result
        int result = largestAreaHistogram(A[0]);

        // iterate over row to find maximum rectangular area
        // considering each row as histogram
        for (int i = 1; i < R; i++) {

            for (int j = 0; j < C; j++)

                // if A[i][j] is 1 then add A[i -1][j]
                if (A[i][j] == 1)
                    A[i][j] += A[i - 1][j];

            // Update result if area with current row (as
            // last row of rectangle) is more
            result = Math.max(result, largestAreaHistogram(A[i]));
        }

        return result;
    }

    private static int largestAreaHistogram(int[] arr) {
        int n = arr.length;
        //we create an empty stack here.
        Stack<Integer> s = new Stack<>();
        //we push -1 to the stack because for some elements there will be no previous
        //smaller element in the array and we can store -1 as the index for previous smaller.
        s.push(-1);
        int max_area = arr[0];
        //We declare left_smaller and right_smaller array of size n and initialize them with -1 and n as their default value.
        //left_smaller[i] will store the index of previous smaller element for ith element of the array.
        //right_smaller[i] will store the index of next smaller element for ith element of the array.
        int left_smaller[] = new int[n];
        int right_smaller[] = new int[n];
        for (int i = 0; i < n; i++){
            left_smaller[i] = -1;
            right_smaller[i] = n;
        }

        int i = 0;
        while (i < n)
        {
            while(!s.empty()&&s.peek()!=-1&&arr[i]<arr[s.peek()]){
                //if the current element is smaller than element with index stored on the
                //top of stack then, we pop the top element and store the current element index
                //as the right_smaller for the popped element.
                right_smaller[s.peek()] = (int)i;
                s.pop();
            }
            if(i>0&&arr[i]==arr[(i-1)]){
                //we use this condition to avoid the unnecessary loop to find the left_smaller.
                //since the previous element is same as current element, the left_smaller will always be the same for both.
                left_smaller[i] = left_smaller[(int)(i-1)];
            }else{
                //Element with the index stored on the top of the stack is always smaller than the current element.
                //Therefore the left_smaller[i] will always be s.top().
                left_smaller[i] = s.peek();
            }
            s.push(i);
            i++;
        }


        for(i = 0; i<n; i++){
            //here we find area with every element as the smallest element in their range and compare it with the previous area.
            // in this way we get our max Area form this.
            max_area = Math.max(max_area, arr[i]*(right_smaller[i] - left_smaller[i] - 1));
        }

        return max_area;
    }

    /*
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
    */
    private static boolean isValidParenthesis(String expr) {
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack
                = new ArrayDeque<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

    /*

    Nearest Smaller Element on Left(Prev) & Right(Next) side of an Array
    Input: n = 6
a = {1, 5, 0, 3, 4, 5}
Output: -1 1 -1 0 3 4
Explaination: Upto 3 it is easy to see
the smaller numbers. But for 4 the smaller
numbers are 1, 0 and 3. But among them 3
is closest. Similary for 5 it is 4.

Solution:-
Using Stack T = O(n) S = O(n)
for Next start from ending of the array
    */
    private static void nearestSmaller(int[] arr) {
        // Create an empty stack
        Stack<Integer> S = new Stack<>();

        // Traverse all array elements
        for (int i = 0; i < arr.length; i++) {
            // Keep removing top element from S while the top
            // element is greater than or equal to arr[i]
            while (!S.empty() && S.peek() >= arr[i]) {
                S.pop();
            }

            // If all elements in S were greater than arr[i]
            if (S.empty()) {
                System.out.print("-1, ");
            } else //Else print the nearest smaller element
            {
                System.out.print(S.peek() + ", ");
            }

            // Push this element
            S.push(arr[i]);
        }
    }
}
