package algorithms.dynamicp;


import java.util.Arrays;
import java.util.HashMap;

// Top Up --> Memoization (Using recursion saving result of sub-problems)
// Bottom Up --> Dynamic Programming (Iteratively calculating result from starting)
public class DPPractice {
    public static void main(String[] args) {
        int n = 10;
        /*int[][] arr = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSumMemo(arr, 0, 0, new HashMap<String, Integer>()));
        System.out.println(minPathSumDP(arr, 3, 3));*/

        /*//m,n = last index
        //System.out.println(uniquePaths(0,0,2, 6));
        //System.out.println(uniquePathsMemo(0,0,2, 6, new HashMap<String, Integer>()));
        System.out.println(uniquePathsDP(2, 6));*/
        /*int values[] = {1,2,3};
        int weight[] = {4,5,1};


        System.out.println(knapSack01Memo(4, weight, values, 3, new int[3]));

        int w = 10;
        n = 4;
        int[] val = {10, 40, 30, 50};
        int[] wt = {5, 4, 6, 3};

        System.out.println(knapSack01DP(w, wt, val, n));*/

        /*n = 8;

        int prices[] = {1, 5, 8, 9, 10, 17, 17, 20};
        rodCutting(prices, n);*/

       /* String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        *//*System.out.println(lcsMemo(str1.length(), str2.length(), str1, str2, new HashMap<String, Integer>()));
        System.out.println(lcs(str1.length(), str2.length(), str1, str2));*//*
        System.out.println(lcsDP(str1.length(), str2.length(), str1, str2));*/
        String s = "geek", t = "gesek";

        System.out.println(editDistanceDP(s, t));

    }

    static int editDistanceDP(String s, String t) {
        int x = s.length();
        int y = t.length();
        int[][] dp = new int[x + 1][y + 1];

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j

                    // If second string is empty, only option is
                    // to remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last
                    // char and recur for remaining string
                else if (s.charAt(i - 1)
                        == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                    // If the last character is different,
                    // consider all possibilities and find the
                    // minimum
                else
                    dp[i][j] = 1
                            + Math.min(Math.min(dp[i][j - 1], // Insert
                            dp[i - 1][j]), // Remove
                            dp[i - 1]
                                    [j - 1]); // Replace

            }
        }
        return dp[x-1][y-1];
    }
    static int editDistance(String s, String t) {
        if(s.length() == 0) return t.length();
        if(t.length() == 0) return s.length();

        if(s.charAt(0) == t.charAt(0)){
            return editDistance(s.substring(1), t.substring(1));
        }
        int insert = editDistance(s, t.substring(1));
        int remove = editDistance(s.substring(1), t);
        int replace = editDistance(s.substring(1), t.substring(1));

        return 1 + Math.min(Math.min(insert, remove), replace);

    }


    //Longest Common Subsequence
    static int lcsDP(int x, int y, String s1, String s2) {
        int[][] arr = new int[x + 1][y + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {

                // We can remove this if block bcz by defult zero is initialized
                /*if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                    continue;
                }*/

                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    System.out.println(s1.charAt(i-1));
                    arr[i][j] = 1 + arr[i-1][j-1];
                }else {
                    arr[i][j] =  Math.max(arr[i-1][j], arr[i][j-1]);
                }

            }
        }
        return arr[x][y];
    }


    //Longest Common Subsequence
    static int lcsMemo(int x, int y, String s1, String s2, HashMap<String, Integer> map) {
        if (x == 0 || y == 0) {
            return 0;
        }

        String key = x + "#" + y;
        if (map.containsKey(key)) return map.get(key);

        if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
            int c = 1 + lcsMemo(x - 1, y - 1, s1, s2, map);
            map.put(key, c);
            return c;
        }

        int withX = lcsMemo(x, y - 1, s1, s2, map);
        int withY = lcsMemo(x - 1, y, s1, s2, map);

        map.put(key, Math.max(withX, withY));
        return Math.max(withX, withY);

    }

    //Longest Common Subsequence
    static int lcs2(int i, int j, String s1, String s2) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return 1 + lcs(i - 1, j - 1, s1, s2);
        }

        int withX = lcs2(i, j - 1, s1, s2);
        int withY = lcs2(i - 1, j, s1, s2);

        return Math.max(withX, withY);

    }
    //or
    static int lcs(int x, int y, String s1, String s2) {
        if (x == 0 || y == 0) {
            return 0;
        }

        //If we found the match
        if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
            return 1 + lcs(x - 1, y - 1, s1, s2);
        }

        //Either increment x or y and take the max of them
        int withX = lcs(x, y - 1, s1, s2);
        int withY = lcs(x - 1, y, s1, s2);
        //Not required
        //int without = lcs(x-1, y-1, s1, s2);

        return Math.max(withX, withY);

    }

    private static int rodCutting(int[] prices, int n) {
        if (n == 0) {
            return 0;
        }

        int without = prices[n - 1] + rodCutting(prices, n - 1);
        int with = rodCutting(prices, n - 1);

        return -1;
    }

    private static int knapSack01DP(int w, int wt[], int val[], int n) {

        // Populate base cases
        int[][] mat = new int[n + 1][w + 1];
        for (int r = 0; r < w + 1; r++) {
            mat[0][r] = 0;
        }
        for (int c = 0; c < n + 1; c++) {
            mat[c][0] = 0;
        }

        // Main logic
        for (int item = 1; item <= n; item++) {
            for (int capacity = 1; capacity <= w; capacity++) {
                int maxValWithoutCurr = mat[item - 1][capacity]; // This is guaranteed to exist
                int maxValWithCurr = 0; // We initialize this value to 0

                int weightOfCurr = wt[item - 1]; // We use item -1 to account for the extra row at the top
                if (capacity >= weightOfCurr) { // We check if the knapsack can fit the current item
                    maxValWithCurr = val[item - 1]; // If so, maxValWithCurr is at least the value of the current item

                    int remainingCapacity = capacity - weightOfCurr; // remainingCapacity must be at least 0
                    maxValWithCurr += mat[item - 1][remainingCapacity]; // Add the maximum value obtainable with the remaining capacity
                }

                mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr); // Pick the larger of the two
            }
        }
        System.out.println(Arrays.deepToString(mat)); // Visualization of the table
        return mat[n][w]; // Final answer

    }

    private static int knapSack01Memo(int w, int wt[], int val[], int n, int[] memoArr) {
        if (w <= 0 || n == 0) {
            return 0;
        }

        if (wt[n - 1] > w) {
            return knapSack01(w, wt, val, n - 1);
        }

        if (memoArr[n - 1] != 0) return memoArr[n - 1];

        int included = val[n - 1] + knapSack01Memo(w - wt[n - 1], wt, val, n - 1, memoArr);
        int notIncluded = knapSack01Memo(w - wt[n - 1], wt, val, n - 1, memoArr);

        memoArr[n - 1] = Math.max(included, notIncluded);
        return Math.max(included, notIncluded);
    }


    // Start from n or 0 doesn't matter
    private static int knapSack01(int w, int wt[], int val[], int n) {
        if (w <= 0 || n == 0) {
            return 0;
        }

        if (wt[n - 1] > w) {
            return knapSack01(w, wt, val, n - 1);
        }

        int included = val[n - 1] + knapSack01(w - wt[n - 1], wt, val, n - 1);
        int notIncluded = knapSack01(w, wt, val, n - 1);

        return Math.max(included, notIncluded);
    }

    //62. Unique Paths
    private static int uniquePathsDP(int m, int n) {
        int[][] arr = new int[m + 1][n + 1];

        /*for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    arr[i][j] = 1;
                }
                else {
                    arr[i][j] = arr[i-1][j] + arr[i][j-1];
                }
            }}
            return arr[m][n];
            */
        // or
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i + 1][j] + arr[i][j + 1];
                }
            }
        }
        return arr[0][0];
    }

    //62. Unique Paths
    private static int uniquePathsMemo(int i, int j, int m, int n, HashMap<String, Integer> map) {
        //got one path
        if (i == m && j == n) return 1;

        //out of index
        if (i > m || j > n) return 0;

        String key = i + "#" + j;
        if (map.containsKey(key)) return map.get(key);

        int down = uniquePaths(i + 1, j, m, n);
        int right = uniquePaths(i, j + 1, m, n);

        map.put(key, down + right);
        return down + right;
    }

    //62. Unique Paths

    private static int uniquePaths(int i, int j, int m, int n) {
        //got one path
        if (i == m && j == n) return 1;

        //out of index
        if (i > m || j > n) return 0;

        int down = uniquePaths(i + 1, j, m, n);
        int right = uniquePaths(i, j + 1, m, n);

        return down + right;
    }

    //64. Minimum Path Sum
    private static int minPathSumDP(int[][] arr2, int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j] = arr2[i][j];
                } else if (i == 0) {
                    arr[i][j] = arr2[i][j] + arr[i][j - 1];
                } else if (j == 0) {
                    arr[i][j] = arr2[i][j] + arr[i - 1][j];
                } else {
                    arr[i][j] = arr2[i][j] + Math.min(arr[i - 1][j], arr[i][j - 1]);
                }

            }
        }
        return arr[m - 1][n - 1];
    }

    //64. Minimum Path Sum
    private static int minPathSumMemo(int[][] arr, int m, int n, HashMap<String, Integer> map) {
        if (m == arr.length - 1 && n == arr[0].length - 1) {
            return arr[m][n];
        }
        //If index out of row or column
        if (m > arr.length - 1 || n > arr[0].length - 1) {
            return Integer.MAX_VALUE;
        }

        String key = m + "#" + n;

        if (map.containsKey(key)) return map.get(key);

        int down = minPathSumMemo(arr, m + 1, n, map);
        int right = minPathSumMemo(arr, m, n + 1, map);

        map.put(key, arr[m][n] + Math.min(down, right));

        return arr[m][n] + Math.min(down, right);
    }

    //64. Minimum Path Sum
    private static int minPathSum(int[][] arr, int m, int n) {
        if (m == arr.length - 1 && n == arr[0].length - 1) {
            return arr[m][n];
        }
        //If index out of row or column
        if (m > arr.length - 1 || n > arr[0].length - 1) {
            return Integer.MAX_VALUE;
        }

        int down = minPathSum(arr, m + 1, n);
        int right = minPathSum(arr, m, n + 1);

        return arr[m][n] + Math.min(down, right);
    }


    private static int fibonacciDP(int n) {
        // T=O(n) & S=O(n);
        /*int[] table = new int[n+1];
        table[0] = 0;
        table[1] = 1;
        for(int i = 2; i <=n; i++){
            table[i] = table[i-1] + table[i-2];
        }
        return table[n];*/

        // T=O(n) & S=O(1);
        int n1 = 0;
        int n2 = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }

        return result;
    }

    private static int fibonacciMemo(int n, HashMap<Integer, Integer> map) {
        if (n <= 1) {
            return n;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int n1 = fibonacciMemo(n - 1, map);
        int n2 = fibonacciMemo(n - 2, map);
        map.put(n, n1 + n2);

        return n1 + n2;
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        // Creating two sub-problems of size almost n at each iteration --> O(2^n)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
