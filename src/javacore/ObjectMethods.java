package javacore;
/*

Everything in JAVA is derived from Object. And Object has 3 imp methods

1. hashCode() --> It is integer number calculated by hashing it helps to store the objects in collections efficiently.
used in HashMap HashSet LinkedHashSet
Contract of hashCode():-

1. hashCode(object) return same value even if invoked multiple time on same object in same App.
2. If two objects are equal according to the equals(Object) method, calling the hashCode() method on each of the two objects must produce the same value.
3. If objects not equal doesn't need to produce same result. But it is better to have different for better performance.

hashCode of primitive data type is always same even if reference are different.
For non-primitive objects choose all fields to calculate hashCode() for better performance

2. equals() --> Used to compare values

== --> compare references(for wrapper classes if object created using new Integer(5) then only references will be different)
equals() --> compare objects value. For non-primitive data types without equals() overridden, it will call Object.equals()
and compare references [Employee@732] & [Employee@733]
public boolean equals(Object obj) {
        return (this == obj);
    }

3. toString() --> For all primitive data types toString() return same value.
But for Non-primitive if didn't override specify explicitly toString()
returns -->  javacore.Employee@58372a00 i.e javacore.Employee@ + hex of hashCode

*/
public class ObjectMethods {
    public static void main(String[] args) {
        //hashCodeAndToString();
        equalsWorking();
    }

    private static void equalsWorking() {
        /*Integer number1 = 1;
        Integer number2 = 1;
        System.out.println(number1 == number2);// true
        System.out.println(number1.equals(number2));//true
*/
        String s1 = "A";
        String s2 = "A";
        String s3 = new String("A");
        System.out.println(s1 == s2);//true
        System.out.println(s1.equals(s2));//true
        System.out.println(s3 == s2);//false
        System.out.println(s3.equals(s2));//true
        System.out.println(s1.hashCode());
        System.out.println(s3.hashCode());

        Employee employee1 = new Employee(1, "A");
        Employee employee2 = new Employee(1, "A");

        //Without overriding
        System.out.println(employee1.hashCode());//1480010240
        System.out.println(employee2.hashCode());//81628611
        System.out.println(employee1.toString());//javacore.Employee@58372a00
        System.out.println(employee2.toString());//javacore.Employee@4dd8dc3
        System.out.println(employee1 == employee2);//false because references are different [Employee@732] [Employee@733]
        System.out.println(employee1.equals(employee2));//false  because we didnt override equals() method.

    }

    private static void hashCodeAndToString() {
        //HashCode()
        Integer number = 1;
        System.out.println(number.hashCode());//1
        System.out.println(number.toString());//1

        String s = "A";
        System.out.println(s.hashCode());//65
        System.out.println(s.toString());//A

        Employee employee1 = new Employee(1, "A");
        System.out.println(employee1.hashCode());//1480010240

        //println internally call toString() of object even if we didn't call
        System.out.println(employee1.toString());//javacore.Employee@58372a00 i.e. (1480010240)10 = (58372A00)16
    }
}

class Employee {
    Integer id;
    String name;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}