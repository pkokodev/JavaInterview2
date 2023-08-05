package java8.funinterfaces;

import java.util.function.*;

/*
Java SE 8 included four main kinds of functional interfaces which can be applied in multiple situations. These are:

1. Consumer ,Bi
2. Predicate ,Bi
3. Function  ,Bi
4. Supplier

*/
public class B_Java8IFs {
    public static void main(String[] args) {

        //Consumer accepts(T t) andThen(Consumer c)
        /*Consumer<Integer> consumer = (x) -> System.out.println(x * 2);
        consumer.accept(10);// 20
        Consumer consumer1 = consumer.andThen(consumer);
        consumer1.accept(10); //20 /n 20*/

        //Bi-Consumer accepts(T t, U u) andThen(BiConsumer bc)

        /*BiConsumer<Integer, String> biConsumer = (n, s) -> System.out.println(s + " " + n);
        biConsumer.accept(10, "Hey");// Hey 10
        biConsumer.andThen(biConsumer).accept(10, "Hey");// Hey 10 /n Hey 10*/

        //Predicate boolean test(T t); Predicate and(Predicate); Predicate or(Predicate);  Predicate negate()..

        /*Predicate<Integer> predicate = (x) -> x > 100;
        System.out.println(predicate.test(100));// false

        System.out.println(predicate.and(predicate).test(100));// false*/

        /*BiPredicate<Integer, Integer> biPredicate = (x, y) -> x > y;

        System.out.println(biPredicate.test(100, 500));// false
        System.out.println(biPredicate.test(1100, 500));// true
        //It is also having same chaining concrete methods as Predicate*/

        //Supplier T get(); only one method no other concrete methods
        /*Supplier<Integer> supplier = () -> 10;
        System.out.println(supplier.get());*/

        //extends BiFunciton However, what distinguishes it from a normal BiFunciton is that both of its arguments and its return type are same.
        /*BinaryOperator<Integer> binaryOperator = (x, y) -> x+y;
        System.out.println(binaryOperator.apply(10, 30));// 40
        //maxBy minBy two static methods which takes comparator as argument

        BinaryOperator<Integer> bp = BinaryOperator.maxBy((x, y) -> x - y);
        System.out.println(bp.apply(10, 20));// 20*/
    }
}
