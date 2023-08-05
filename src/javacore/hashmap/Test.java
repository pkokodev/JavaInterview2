package javacore.hashmap;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        for(int i = 0; i < 17; i++){
            hashMap.put(i,"A" + i);
        }
        System.out.println(hashMap.size());
        System.out.println(hashMap);
    }
}
