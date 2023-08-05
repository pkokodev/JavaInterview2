package algorithms.dynamicp;

public class Test {
    public static void main(String[] args) {
        System.out.println(fibonacci(6, 0));
    }

    private static int fibonacci(int n, int sum) {
        if (n <= 1) {
            return n;
        }
        sum += fibonacci(n - 1, sum) + fibonacci(n - 2, sum);
        return sum;
    }
}
