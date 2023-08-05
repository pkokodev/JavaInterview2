package arrays;

public class StringTest {
    public static void main(String[] args) {
        String s = "Amazon";
        String s2 = new String("Google");
        revString(s.toCharArray());
        System.out.println(isPalindrome("abcbam".toCharArray()));
    }

    private static void revString(char[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (l < r){
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++; r--;
        }
        System.out.println(arr);
    }
    private static boolean isPalindrome(char[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (l < r){
            if(arr[l] != arr[r]) return false;
            l++; r--;
        }
        return true;
    }
}
