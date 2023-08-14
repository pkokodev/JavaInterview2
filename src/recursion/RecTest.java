package recursion;
/*
Recursion :- Base Cases + Recursive Cases (calling same function)
To understand how the recursive calls are made, always visualize as tree
Tree can be traversed by two ways 1. DFS 2. BFS
In recursion, DFS is the only way to traverse
Trick:- At each step Always see number of ways or all the possible solution to the problem to solve recursion problems
or Break down problem into sub-problems like merge sort

e.g. How many ways can we reach to n steps taking 1 or 2 steps at each time
                      5
           1 step /      \ 2 step
                 4(5-1)   3(5-2)   ...so on

So recursive eq -> f(n) = f(n-1) + f(n-2)
Base case -> n = 1; 1 & n = 2; 2

At each stage 2 possibilities, so 2-ary tree possible and 2 recursive calls f(n-1) or f(n-2)
*/
public class RecTest {
    public static void main(String[] args) {
     //Sum of numbers sum = n * (n+1)/2
        System.out.println(getSum(10));
        System.out.println(getSumIterative(10));
        System.out.println(getSumRecursive(10));
        System.out.println("--------------nof way to reach n steps with 1 or 2 steps-----------------");
        System.out.println(nofWaysSteps(6));
    }

    //Using formula
    private static int getSum(int n) {
        return n * (n+1)/2;
    }
    //Using Iteration
    private static int getSumIterative(int n) {
        int sum = 0;
        for (int i = n; i > 0; i--){
            sum = sum + i;
        }
        return sum;
    }
    //Using Recursion
    private static int getSumRecursive(int n) {
        if(n == 0) return 0;
        int sum = n + getSumRecursive(n-1);
        return sum;
    }




    //nof way to reach n steps with 1 or 2 steps
    /*
    n = 1 -> (1) -> 1
    n = 2 -> (1,1) + (2) -> 2
    n = 3 -> (1,1,1) + (1,2) + (2,1) -> 3
    n = 4 -> (1,1,1,1) + (1,1,2) + (2,1,1) + (2,2) -> 4

   Still not able to understand pattern

   IMP:- Always think how many
     */

    private static int nofWaysSteps(int n) {
        if(n == 1 || n == 2) return n;
        return nofWaysSteps(n-1) + nofWaysSteps(n-2);
    }
}
