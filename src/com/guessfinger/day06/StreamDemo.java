package com.guessfinger.day06;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author : Mx
 * @Description:
 * @Date: Created in 10:24 2019/4/17
 */
public class StreamDemo {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        set.stream()
                .limit(2)
                .forEach(System.out::println);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.stream()
                .limit(5)
                .forEach(System.out::println);

        // 输出每个单词的长度 下面的可以直接foreach() 不用再次转换了
        List<String> list1 = Arrays.asList("java8", "in", "action");
        list1.stream()
                .map(String::length)
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);

        // 查找单词中不重复的字符
        List<String> list2 = Arrays.asList("java8", "in", "action");
        list2.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);


        // 上面的不能满足需求 我们需要使用flatMap
        list2.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);

        // 给定一个数组 然后返回这个数组的平方
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4);
        list3.stream()
                .map(i -> i*i)
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);
        // 给定列表后 返回的是   [(1,4),(1,5),(1,6),(2,4)...]
        // 扁平化处理
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        l1.stream()
                .flatMap(i -> l2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);
        // 如果要求数组纸盒能够被3整除的数对呢
        // 你说这个理解吧！不是很理解 但是通过读取其中的代码块 可以简单理解一下
        l1.stream()
                .flatMap(i -> l2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        // 检查谓词是否匹配一个元素
        System.out.println(l1.stream().anyMatch(i -> i > 2));
        // 检查谓词是否匹配所有的元素
        System.out.println(l1.stream().allMatch(i -> i > 2));
        // 检查所有的元素都不匹配元素
        System.out.println(l1.stream().noneMatch(i -> i >3));
        // 显式的判断有没有findAny()有没有找到元素
        System.out.println(l1.stream().filter(i -> i > 0).findAny().isPresent());
        // 如果找到了 执行相关的代码
        l1.stream().filter(i -> i >0).findAny().ifPresent(System.out::println);
        // 如果存在值就返回 如果不存在的话抛出一个异常
        System.out.println(l1.stream().filter(i -> i > 1).findAny().get());
        System.out.println(l1.stream().filter(i -> i > 0).findAny().orElse(10));
        // 给定一个数组 找出第一个平方数能被3整除的数
        System.out.println("------------------");
        List<Integer> findFirstList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer x = findFirstList.stream()
                .filter(i -> i * i % 3 == 0)
                .findFirst().get();
        System.out.println(x);
        Integer y = findFirstList.stream()
                .filter(i -> i * i % 3 == 0)
                .findAny().get();
        System.out.println(y);

        // 规约操作
        int sum = findFirstList.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        System.out.println(findFirstList.stream().reduce(0,Integer::sum));
        System.out.println(findFirstList.stream().reduce(Integer::sum));
        // 最大值 最小值
        System.out.println(findFirstList.stream().reduce(Integer::max));
        System.out.println(findFirstList.stream().reduce(Integer::min));
    }
}