package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((a, b) -> a * b);
        System.out.println(multiplication.get());

        IntStream parallel = IntStream.range(1, 100).parallel();
        System.out.println(parallel.isParallel());
        IntStream sequential = parallel.sequential();
        System.out.println(sequential.isParallel());

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().parallel().peek(System.out::println).toList();

        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5);
        list3.stream().parallel().forEach(System.out::println);

        List<Integer> list4 = Arrays.asList(1, 3, 4, 5, 2);
        list4.stream().parallel().forEachOrdered(System.out::println);
    }
}