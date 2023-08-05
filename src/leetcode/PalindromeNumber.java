package leetcode;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindromeString("abac"));
    }

    private static boolean isPalindrome(int number) {
        int reverse = 0;
        int temp = number;
        while (number > 0){
            reverse = (reverse * 10) + (number % 10);
            number = number/10;
        }
        return reverse == temp;
    }


    //  n/2 Comparisons optimal
    private static boolean isPalindromeString(String str) {
        for (int i = 0, j = str.length()-1; i < j; i++, j--) {
            if(str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }
}
