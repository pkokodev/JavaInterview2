package arrays;

import java.util.Arrays;
import java.util.stream.Stream;

/*
StringBuilder:- not syncronized hence faster than StringBuffer
StringBullder is mutable hence will not create new string for append but String will create new String for concat.

*/
public class StringPractice {
    public static void main(String[] args) {
        myFunc("abcabcdef");
    }

    /*
    Accept string as input and remove duplicate characters in the string
        and display remove char count
        Input:- abcabcdef
        Output:-abcdef
        a-2,b-2,c-2
        */

    static void myFunc(String str) {
        boolean[] arrAZ = new boolean[26];

        for (int i = 0; i < str.length(); i++) {
            int asci = str.charAt(i) - 'a';
            if(!arrAZ[asci]){
                arrAZ[asci] = true;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < arrAZ.length; i++) {
            if(arrAZ[i]){
                char c = (char) (i + 'a');
                stringBuilder.append(c);
            }

        }

        str = stringBuilder.toString();
        System.out.println(str);
    }

    //Using String: O(n^2)

    static void rev2(){
        String str = "abcd";
        int a = str.length();

        String reverse = "";

        for (int i = a - 1; i >= 0; i--) {
            //String is immutable so new string created and it takes O(n)
            reverse = reverse + str.charAt(i);
        }
        System.out.println("Reverse of String abcd using invert array is :" + reverse);
    }

    //Using StringBuilder: O(n)

    static void rev1(){
        String str = "abcd";
        int a = str.length();
        StringBuilder sb1 = new StringBuilder();

        for (int i = a - 1; i >= 0; i--) {
            //Mutable it just append char at end hence O(1)
            sb1 = sb1.append(str.charAt(i));
        }
        System.out.println("Reverse of String abcd using StringBuilder is :" + sb1);
    }
}