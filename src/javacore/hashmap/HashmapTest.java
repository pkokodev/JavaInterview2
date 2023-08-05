package javacore.hashmap;

import java.util.HashMap;
import java.util.Map;
/*
Hashmap working:- First calculate hashCode() then using mod15 try to insert element at map
 if collision then check if keys are equal using equals(), if equals replace the value otherwise link the node to
 existing's Node next pointer. In Below example,map size will be one because Employee class overrides hashCode() -> 1 & equals -> true.
If object reference are not equals hence calculate different hashCode() by default.
For primitive datatype both methods are already override.
Hashmap is unordered hence don't have any index like 0 to n
*/
public class HashmapTest {
    public static void main(String[] args) {
        Map<Employee, String> map = new HashMap<>();
        Employee employee1 = new Employee(1, "emp1");
        Employee employee2 = new Employee(1, "emp2");
        map.put(employee1, employee1.name);
        map.put(employee2, employee2.name);
        System.out.println(map.size());
    }
}

/*
5

If you are referring to the asymptotic time complexity then:

since HashMap uses hashCode to calculate which bucket to use in the hashtable if you return 1 from hashCode
you effectively make your HashMap's performance be like an (unsorted) LinkedList's performance.

Returning random values will simply blow your HashMap up since equal objects will no longer have equal hashCodes.

Excerpt from Wikipedia:

+----------------------+----------+------------+----------+--------------+
|                      |  Insert  |   Delete   |  Search  | Space Usage  |
+----------------------+----------+------------+----------+--------------+
| Unsorted linked list | O(1)*    | O(1)*      | O(n)     | O(n)         |
| Hash table           | O(1)     | O(1)       | O(1)     | O(n)         |
+----------------------+----------+------------+----------+--------------+
So to sum it up you lose:

Time complexity when searching your HashMap (from O(1) to O(n))
lookup in your HashMap (it won't work anymore)
*/