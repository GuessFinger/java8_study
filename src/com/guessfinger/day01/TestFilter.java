package com.guessfinger.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * create by GuessFinger on 2019/4/7
 **/
public class TestFilter {
    public static void main(String[] args){
        Apple apple1 = new Apple("red", 1);
        Apple apple2 = new Apple("green", 2);
        List<Apple> appleList = new ArrayList<Apple>();
        appleList.add(apple1);
        appleList.add(apple2);


        /**
         *  问题是这样的 有一个Apple的list 你需要从中进行筛选 筛选出颜色符合一定要求的 重量符合一定要求的
         *  java8之前的话 不好一点就是你写两个方法 然后分别进行循环 条件判断
         *  好一点的话 写类似isHeavyApple的方法 然后再循环中调用
         *  java8 以后的话 你在调用的时候直接把这个方法传递进去  这个目的还是让代码能够重复利用一部分
         *  这里理解的还不是很清楚 等到详细讲解的时候再进行学习
         */
        List<Apple> apples = filterApple(appleList,Apple::isHeavyApple);
        System.out.println(apples.get(0).getWeight());
        /**
         *  方法作为值传递的时候确实很方便 但是我们需要定义大量的像isHeavyApple 也不是很方便
         *  所以这里就引入了lambda表达式/匿名函数
         *  这样的写法还是依托于上面的基础 就是方法的作为值传递
         */

        List<Apple> apples1 = filterApple(appleList, (Apple a) -> "green".equals(a.getColor()));
        // 甚至还能这么写
        List<Apple> apples2 = filterApple(appleList, (Apple a) -> "green".equals(a.getColor())
                || 1< a.getWeight());
        // 这里运用流的方式进行过滤
        List<Apple> apples3 = appleList.stream().filter((Apple a) -> "green".equals(a.getColor()))
                .collect(toList());

    }


    public interface Predicate<T> {
        boolean test(T t);
    }

     static List<Apple> filterApple(List<Apple> appleList,Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : appleList) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

