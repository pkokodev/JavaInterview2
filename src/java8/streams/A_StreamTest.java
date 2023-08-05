package java8.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class A_StreamTest {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> result = numbers.stream().map((x) -> x * x).collect(Collectors.toList());//[4, 9, 16, 25]
        //List<Integer> result = numbers.stream().filter((x) -> x > 3).collect(Collectors.toList());//[4, 5]
        //Optional<Integer> result = numbers.stream().reduce((x, y) -> x+y); //14 reduce is terminal operation Optional<T> reduce(BinaryOperator<T> accumulator);

        //Sum of squares of numbers
        //Optional<Integer> result = numbers.stream().map(x -> x * x).reduce((x, y) -> x + y);// 54

        //Sum of squares of numbers > 3
        //Optional<Integer> result = numbers.stream().filter(x -> x > 3).map(x -> x * x).reduce((x, y) -> x + y);// 41
        //or
        //Optional<Integer> result = numbers.stream().filter(x -> x > 3).reduce((x, y) -> x * x + y * y);// 41
        //Integer result = numbers.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);//6
        //List<Integer> result = numbers.stream().sorted((x, y) -> y - x).collect(Collectors.toList());//[5, 4, 3, 2]
        //System.out.println(result);


        //collect(Collectors.methods) Most important terminal operation on stream. Collectors is the class

        //Print Average Of All Numbers
        System.out.println(
                numbers.stream()
                        .reduce(0, (x, sum) -> x + sum )/numbers.size()
        );

        //Or

        System.out.println(
                numbers.stream().mapToInt(x -> x).average().getAsDouble()
        );

        //Print Even & Odd Numbers using Streams
        System.out.println(
                numbers.stream().collect(Collectors.partitioningBy(x -> (x & 1) == 0))
        );

        //Print Duplicate Numbers using Streams
        System.out.println(
                numbers.stream().filter(i -> Collections.frequency(numbers, i) >1)
                        .collect(Collectors.toSet())
        );

        //Get/ignore first 2 numbers using Limit & Skip in Streams
        System.out.println(
                numbers.stream()
                        .limit(2).mapToInt(x -> x).sum()
        );

        System.out.println(
                numbers.stream()
                        .skip(2).mapToInt(x -> x).sum()
        );
    }
}
