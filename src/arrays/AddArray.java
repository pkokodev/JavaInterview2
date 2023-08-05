package arrays;

import java.util.Scanner;

public class AddArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        int m = sc.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) b[i] = sc.nextInt();
        sc.close();

        calSum(a, b, n, m);
    }
    static void calSum(int a[], int b[], int n, int m) {
        if (n==m)
        {
            int c[] = new int[n];
            for (int i=0; i<n; i++)
            {
                c[i]=a[i]+b[i];
                System.out.println(c[i]);
            }
        }
        else if (n<m)
        {
            int c[] = new int[m];
            for (int i=0; i<m-n; i++)
            {
                c[i] = b[i];
            }

            for (int i=0; i<n; i++)
            {
                c[n+i]=a[i]+b[i+(m-n)];
            }
            for (int i=0; i<m; i++)
            {
                System.out.println(c[i]);
            }
        }
        else
        {
            int c[] = new int[n];
            for (int i=0; i<n-m; i++)
            {
                c[i] = a[i];
            }

            for (int i=0; i<m; i++)
            {
                c[m+i]=b[i]+a[i+(n-m)];
            }
            for (int i=0; i<n; i++)
            {
                System.out.println(c[i]);
            }
        }



    }
}

