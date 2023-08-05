package javacore.inheritance;

public class InheritanceTest {
    public static void main(String[] args) {
        Child child = new Child();
        Parent parent = new Parent();
        parent.show(null); // Ambiguous call if show() is accepting not-primitive datatype
    }
}
