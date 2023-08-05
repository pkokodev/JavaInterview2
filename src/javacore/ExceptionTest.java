package javacore;

public class ExceptionTest {
    public static void main(String[] args) {
        Child child = new Child();
        child.print();
    }
    public static class Parent {
        public void print() {
            System.out.println("Parent");
        }

    }

    public static class Child extends Parent {
        @Override
        public void print() {
            System.out.println("Child");
            throw new RuntimeException();
        }
    }
}


