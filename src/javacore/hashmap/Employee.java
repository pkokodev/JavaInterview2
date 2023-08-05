package javacore.hashmap;

public class Employee {
     int id;
     String name;

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
