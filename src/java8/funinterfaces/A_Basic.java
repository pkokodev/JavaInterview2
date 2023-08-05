package java8.funinterfaces;

/*
Everything in java is objects. But we can not create objects of abstract classes and interfaces directly.
We need to create another new class which can extends the abstract class or implements the interface
and then create the object of that new class to use it.

Or create anonymous class.

JAVA is OOP. But to allow functional programming they have introduced Functional interface in JAVA8
We can treat them like callback functions in javascript.

If interface is having only one abstract method then we can say that interface as "Functional interface"
After java 8 interface can have concrete methods as well.(default and static methods)

Single Abstract Method Interfaces SAM

Lambda expressions basically express instances of functional interfaces (An interface with single abstract method is called functional interface.
An example is java.lang.Runnable).
lambda expressions implement the only abstract function and therefore implement functional interfaces

reduce boilerplate code

After Java 8 -> Interfaces can have default and static methods. Eg. Iterable.forEach() default method i.e. adding new functionality without
breaking the existing implementation.
MyEg. interface PaymentService abstract doPayment() UpiImpl CardImpl => Suppose we want new feature default validate(Predicate arg), isTwoFactor()
common feature static transactionHistory(Supplier arg),sendReport(Bi-Function from, To)

Abstract Class vs interface
Shared properties => abstract class. Eg. Abclass Notification with constructor(type) setSender() setReceiver() WPNot SMSNot PushNot
Shared functionality => interface. Eg. interface NotificationService send(Notification arg)
*/

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.function.BiFunction;
import java.util.function.Function;

public class A_Basic {
    public static void main(String[] args) {

        //Here Runnable is interface and only run() abstract method is present so it is functional interface
        // runnable is object here
        Runnable runnable = new Thread(new Runnable() {
            @Override public void run()
            {
                System.out.println("New thread created " + Thread.currentThread().getName());
            }
        });
        runnable.run(); // will be called on main thread because so far it is just a object of functional interface not hte thread, to run on other thread need to call thread.start()

        //Or Using lambda

        Runnable runnable2 = new Thread(() -> System.out.println("New thread created " + Thread.currentThread().getName()));

        /*
        public interface Function<T, R> {

        Applies this function to the given argument.

        @param t the function argument
        @return the function result

        R apply(T t);

        2 concrete methods
        compose(){}; This method is not available in BiFunction
        andThen(){};
        }
        */

        Function<Integer, String> functionObj = x -> "Multiplication is: " + (x * 2);
        //System.out.println(functionObj.apply(10)); // output:- Multiplication is: 20


        //Used BiFunction here bcz it accepts two args and return one. Here taking two int and returnig int
        BiFunction<Integer, Integer, Integer> addTwoNumbers = (x, y) -> x+y;
        //System.out.println(addTwoNumbers.apply(10, 20)); // output:- 30

        //------------------andThen
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;

        // BiF andThen(F f) accepts function returns BiF
        BiFunction<Integer, Integer, Integer> addTwoNumbersFirstAndThenMultiplyByTwo = addTwoNumbers.andThen(multiplyByTwo);

        //f.apply(apply(t)) i.e g(f(x))
        //System.out.println(addTwoNumbersFirstAndThenMultiplyByTwo.apply(10, 30)); // output:- 80



        //-------Few IMP Already presented interfaces converted into functional interface----

        // Runnable –> This interface only contains the run() method.

        // Comparable –> This interface only contains the compareTo() method. int compareTo(T o)
        //takes object to compare and return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.

        Comparable<Integer> comparable = (x) -> 0;

        //System.out.println(comparable.compareTo(12)); // Output:- 0

        Comparable<Integer> compareWith100 = (x) -> x - 100;

        //System.out.println(compareWith100.compareTo(12)); // Output:- -88 means less than
        //System.out.println(compareWith100.compareTo(100)); // Output:- 0 means equal
        //System.out.println(compareWith100.compareTo(150)); // Output:- 50 means greater than

        //But two compare two objects we hava Comparator<T> having abstract method int compare(T o1, T o2);
        Comparator<Integer> comparator = (x, y) -> x-y;
        //System.out.println(comparator.compare(12, 100)); // Output:- -88 means  x < y
        //System.out.println(comparator.compare(100, 100)); // Output:- 0 means  x = y
        //System.out.println(comparator.compare(150, 100)); // Output:- 50 means  x > y


        /*--------------Comparable vs Comparator---------------

        In both, The sorting order is decided by the return value.
        Collections.sort(List<T> list) --> must be T implement Comparable otherwise exception. So only one sorting sequence.
        Collections.sort(List<T> list, Comparator) --> must be T implement Comparable otherwise exception. So only one sorting sequence.

        So we can use Comparable default sorting sequence, and if we want to sort on multiple filds then create Comparaotrs and pass them
        as second parameter.

        Comparable:- comparing itself with another object --> Collections.sort(empList) otherwise gives excetion
        If Employee class implements Comparable then collections will be sort based on compareTo(Employee emp){this.salary - emp.salary}

        Comparator:- compare two passed objects --> Collections.sort(empList, comparator)
        If want to compare on different properties on different time. CompareByName CompareByAge
        class AgeCompare implements Comparator<Employee>{
            public int compare(Employee m1, Employee m2){
                if (m1.getAge() < m2.getAge()) return -1;
                if (m1.getAge() > m2.getAge()) return 1;
                else return 0;
                }}

        class NameCompare implements Comparator<Employee>{
           public int compare(Employee emp1, Employee emp2){
           we can use compareTo method if Employee class already implements Comparable
               return emp1.getName().compareTo(emp2.getName());
        }}

        Both are used to sort collections of custom datatype Collections.sort(empList) or Collections.sort(empList, comparator);
        stream().max(Comparator)
        stream().sorted(Comparator)
        */



        // ActionListener –> This interface only contains the actionPerformed() method.
        
        // Callable –> This interface only contains the call() method.

        Callable<String> callable = () -> "From thread: " + Thread.currentThread().getName();
        FutureTask<String> futureTask = new FutureTask<>(callable);
         Thread thread = new Thread(futureTask);
         thread.start();
        try {
            System.out.println(futureTask.get());//From thread: Thread-2
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
/*
class Test {
    public static void main(String args[])
    {
// create anonymous inner class object
        new Thread(new Runnable() {
            @Override public void run()
            {
                System.out.println("New thread created");
            }
        }).start();
    }
}*/
