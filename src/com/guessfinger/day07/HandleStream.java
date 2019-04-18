package com.guessfinger.day07;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author : Mx
 * @Description:
 * @Date: Created in 10:49 2019/4/18
 */
public class HandleStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list.stream().reduce(0, Integer::sum));
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
        System.out.println(list.stream().mapToInt(Integer::intValue).max());
        OptionalInt x = list.stream().mapToInt(Integer::intValue).max();
        IntStream evenStream = IntStream.range(0, 100).filter(n -> n % 2 == 0);
        System.out.println(evenStream.count());
        OptionalInt xStream = list.stream().mapToInt(Integer::intValue).min();
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(1 + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{1, b, (int) Math.sqrt(1 + b * b)});

        Stream<int[]> xxx = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        xxx.limit(5)
                .forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));

        Stream<String> stream = Stream.of("java", "in", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        int[] intA = {1, 2, 3, 4};
        int sumx = Arrays.stream(intA).sum();
        System.out.println(sumx);


        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        //  斐波纳契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + " " + t[1] + ")"));
    }
}
