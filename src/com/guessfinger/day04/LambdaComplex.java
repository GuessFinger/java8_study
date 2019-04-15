package com.guessfinger.day04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * create by GuessFinger on 2019/4/15
 **/
public class LambdaComplex {
    public static void main(String[] args) {
        // 前面我们用Comparator.comparing 根据比较键值的Function获得一个Comparator对象
        Comparator<Apple> c1 = Comparator.comparing(Apple::getWeight);
        // 1.逆序 如果你想要对苹果的重量逆序排列怎么办呢？ 我们不用另建立一个Comparator
        // 可以使用接口的默认方法 reversed() 下面这个就是按照重量递减的情况进行排序
        new ArrayList<Apple>().sort(Comparator.comparing(Apple::getWeight).reversed());
        // 2.比较器链 这里的问题就是 如果两个苹果的重量一样的情况下 那么我们按照什么进行排序
        // 我们可以使用.thenComparing() 这个idea都是有智能提示的
        new ArrayList<Apple>().sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));

        // 谓词复合
        // 谓词接口包含3个方法 negate and or
        // 可以使用negate方法返回一个predicate的非  比如不是红色的苹果
        //  Predicate<Apple> notRedApple = redApple.negate()
        // 可以尽心复合
        // redApple.and(a -> a.getWeight() > 150).or(a ->"green".equals(a.getColor()));
        // 这里注意 and 和or 方法是按照表达式链中的位置 从左向右确定优先级别的
        // a.or(b).and(c)  (a || b) && c


        // 函数复合 你可以把Function接口所代表的lambda表达式复合起来 function接口为此分配了andThen和
        // compose 两个默认方法 都会返回一个Function实例
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        Function<Integer, Integer> f3 = f1.andThen(f2);
        int result = f3.apply(2); // 这里的意思就是给定一个数字先加1然后再乘以2 返回值4
        // 你也可以使用compose方法
        Function<Integer, Integer> ff1 = x -> x + 1;
        Function<Integer, Integer> ff2 = x -> x * 2 ;
        Function<Integer, Integer> ff3 = ff1.compose(ff2);
        int result2 = ff3.apply(1); // 这里返回值是3 下面就说明两者之间的区别
        // 总体看来主要的区别就是 andThen()是先执行前面的然后执行后面的
        // compose()是先执行里面的然后执行外面的

    }
}
