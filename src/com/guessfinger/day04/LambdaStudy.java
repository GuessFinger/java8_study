package com.guessfinger.day04;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * create by GuessFinger on 2019/4/15
 **/
public class LambdaStudy {
    /**
     * 我们这里的重点是 用不同的策略给一个Apple列表进行排序
     */

    // 第一步：代码传递
    // 因为List已经提供了sort方法 如何把排序的策略传递给sort呢？ 我们这里先将行为参数化
    public static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public static void main(String[] args){
    // 我们这里如果调用第一步的话
        new ArrayList<Apple>().sort(new AppleComparator());

    // 第二步 我们使用匿名函数
        new ArrayList<Apple>().sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

    // 第三步 在需要使用函数式接口的地方我们使用lambda表达式  comparator<Apple> 就是函数式接口
        new ArrayList<Apple>().sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        // 我们之前 解释过我们通过类型推荐  这里可以不写具体的类型
        new ArrayList<Apple>().sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //  new ArrayList<Apple>().sort(Comparator.comparing(Apple::getWeight));
        // 其实这里idea已经进行智能提示了 就是你上面的代码是可以进行优化的
        // Comparator 具有一个叫做comparing的静态辅助方法 它可以接受一个Function来提取Comparable的值
        // 并生成一个Comparator对象
        Comparator<Apple> c1 = Comparator.comparing((Apple a) -> a.getWeight());
        new ArrayList<Apple>().sort(Comparator.comparing((Apple a) -> a.getWeight()));
    // 第四步 方法的引用 这里应该是属于方法引用的第二种
        new ArrayList<Apple>().sort(Comparator.comparing(Apple::getWeight));
    }

}
