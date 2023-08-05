package arrays;

//No. of times outer loop run = No. of rows
//No. of times inner loop run = No. of cols
public class StarPattern {
    public static void main(String[] args) {
        pattern8(5);

    }

    static void pattern8(int n) {
        /*

             *
            ***
           *****
          *******
         *********

         */

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if(col <= (n - row)){
                    System.out.print("  ");
                }else {
                    System.out.print("* ");
                }

            }

            for (int col = 2; col <= row; col++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    static void pattern7(int n) {
        /*

         *****
          ****
           ***
            **
             *

         */

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if(col <= (row - 1)){
                    System.out.print("  ");
                }else {
                    System.out.print("* ");
                }

            }

            System.out.println();
        }
    }
    static void pattern6(int n) {
        /*

             *
            **
           ***
          ****
         *****

         */

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if(col <= (n - row)){
                    System.out.print("  ");
                }else {
                    System.out.print("* ");
                }

            }

            System.out.println();
        }
    }

    static void pattern5(int n) {
        /*

         *
         **
         ***
         ****
         *****
         ****
         ***
         **
         *

         */

        for (int row = 1; row <= n*2; row++) {
            if(row <=n ){
                for (int col = 1; col <= row; col++) {
                    System.out.print("* ");
                }

                System.out.println();

            }else {
                for (int col = 2; col <= (n*2 - row + 1); col++) {
                    System.out.print("* ");
                }
                System.out.println();
            }

        }
    }

    static void pattern4(int n) {
        /*

    1
    1 2
    1 2 3
    1 2 3 4
    1 2 3 4 5

         */

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print( col + " ");
            }

            System.out.println();
        }
    }

    static void pattern3(int n) {
        /*

         *****
         ****
         ***
         **
         *

         */

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= (n - row + 1); col++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    static void pattern2(int n) {
        /*

         *
         **
         ***
         ****
         *****

         */
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    static void pattern1(int n) {
        /*

         *****
         *****
         *****
         *****
         *****

         */
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
