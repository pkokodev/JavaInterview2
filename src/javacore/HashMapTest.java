package javacore;

import java.util.HashMap;
import java.util.Objects;

/*
Tree means ordered sorted.
HashSet or TreeSet(Ordered sorted on value) Implements Set Interface -> use hashcode for value -> so no duplicate values
HashMap or TreeMap(Ordered sorted on key) Implements Map Interface -> use hashcode for key -> so no duplicate keys

Using ArrayList makes sense when it is important to maintain the same order of items,
and quick access time based on the position/index is an important criterion.

Using LinkedList makes sense when maintaining the same order of items and quick insertion time (adding and removing items at any position)
is an important criterion.

Using HashMap makes sense only when unique keys are available for the data we want to store.
We should use it when searching for items based on a key and quick access time is an important requirement.

HashMap Internal Working

transient Node<K,V>[] table; Array of Nodes

static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
        }
1. Calculate hashCode()
2. find th bucket using hashCode()%hashMap capacity


if(collision) {
    check with equals method if the key is same
    if(equals()) {
         override the value against that key
         size will not change
    } else  {
        create new Node and next of current will point to that
        size++
  }
} else {
    aded to the bucket
    size++
}

Better the hashing implementation better is the performance
IntelliJ implementation
@Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

--------------Working-------
In hashmap order of the element is not guaranteed, also no indexes. So only way to access values using key
map.keySet() map.values() map.entrySet()

Array of Nodes{hashCode, key, value, Next node}:-
bucket = hashCode(key) & (n-1) ; Default capacity is 16
Objects.hash(null) = 0; So only one null key inserted at 0th bucket for next subsequent null it will override 0th bucket
TREEIFY_THRESHOLD = 8; capacity > 8 creates Balanced tree
UNTREEIFY_THRESHOLD = 6; capacity < 6 creates linked list
 get(key) --> calculate hashcode -> goto that bucket -> find the key and get the value

 average case 0(1) worst case = O(logn)
The Initial Capacity is essentially the number of buckets in the HashMap which by default is 2^4 = 16. A good HashMap algorithm will distribute an equal number of elements to all the buckets.

Default Capacity 16 Threshold 75% If 13 elements come capacity becomes double to maintain logarithmic time complexity
*/
public class HashMapTest {
    public static void main(String[] args) {
        hashMapWorking();


    }

    private static void hashMapWorking() {

        HashMap<Employee,String> hashMap1 = new HashMap<>();

        Employee employee1 = new Employee(1, "A");
        Employee employee2 = new Employee(1, "A");

        //hashCode(employee1) called if not override then calls Object.hasCode() and reference of object used as key in the map
        hashMap1.put(employee1, "first");//key = Employee@727
        hashMap1.put(employee2, "second");//Employee@728
        hashMap1.put(null, "three");
        hashMap1.put(null, "four");
        EmployeeNew[] employeeNews = new EmployeeNew[50];//Array creation
        System.out.println(hashMap1);//{null=four, javacore.Employee@58372a00=first, javacore.Employee@4dd8dc3=second} giving this bcz not overloaded toString()

        HashMap<EmployeeNew,String> hashMap2 = new HashMap<>();

        EmployeeNew employeeNew1 = new EmployeeNew(1, "A");//[EmployeeNew@731] used as key in map
        EmployeeNew employeeNew2 = new EmployeeNew(1, "A");//[EmployeeNew@866]

        //calculate hashCode() if same then call equals()
        hashMap2.put(employeeNew1, "first");//key = [EmployeeNew@731]
        //since hashcode is same then checks equals
        //in equals implementation{ references are not same [EmployeeNew@731] not equal to [EmployeeNew@866]
        // so again will check values as per our mentioned condition. Values are same so it will override the value for same key [EmployeeNew@731]
        hashMap2.put(employeeNew2, "second");

        //After overriding toString()
        System.out.println(hashMap2);//{EmployeeNew{id=1, name='A'}=second} giving result like this bcz overrided toString()
    }
}
class EmployeeNew {
    Integer id;
    String name;

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        // if both the object references are referring to the same object.
        if(this == obj) return true;
        EmployeeNew employeeNew = (EmployeeNew) obj;
        return employeeNew.id.equals(this.id) && employeeNew.name.equals(this.name);
    }

    @Override
    public String toString() {
        return "EmployeeNew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public EmployeeNew(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}