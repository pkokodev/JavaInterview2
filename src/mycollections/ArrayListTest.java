package mycollections;

import java.util.*;

/*
If we change same collection object while iterating(loop, foreach, iterator) then ConcurrentModificationException
hasNext() next() remove()

remove() no exception;

ListIterator is direction hasPrevious() previous()

*/
public class ArrayListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ds");
        list.add("cx");
        list.add("cx");
        list.add("ww");
        list.add("ds");
        list.add("cx");
        list.add("cx");
        list.add("ww");
        list.add("ds");
        list.add("cx");
        list.add("last");
    }
}
