package com.guessfinger.day02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * create by GuessFinger on 2019/4/8
 **/
public class TestFilter {


    /**
     * 可能刚开始的时候农民的需求就是筛选出来律绿色的苹果
     * 我们可以这样写
     */
    public static List<Apple> filterApple(List<Apple> appleList) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if ("green".equals(apple.getColor())) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 需求发生变化了 这时候农民有需要筛选红色的苹果了
     * 为了应对不同的颜色的 可能你这里有把颜色当做参数进行传递了
     * 那么在调用的时候就可以
     * filterAppleByColor(appleList,"red")
     * filterAppleByColor(appleList,"green")
     */
    public static List<Apple> filterAppleByColor(List<Apple> appleList, String color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (color.equals(apple.getColor())) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 农民伯伯现在又有需求就是要筛选轻苹果和重苹果
     * 这时候我们进行简单的拓展 想到未来可能这个重量还是要发生改变的
     */
    public static List<Apple> filterAppleByWeight(List<Apple> appleList, Integer weight) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (weight < apple.getWeight()) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 可能我们还是会进行升级，通过添加一个标志位来判断 到底是颜色过滤还是 就是true/false
     * 经过书中这么一说 主要的问题是 你true/false 表示的含义不是很明了  还有就是你的代码未来的可拓展性非常低
     * 应该是根本就没有可拓展性
     * 这个号尴尬啊 添加的阿里的插件提示不要在判断中添加这么复杂的语句
     */

    public static List<Apple> filterAppleByColorAndWeight(List<Apple> appleList, String color,
                                                          Integer weight, boolean flag) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if ((flag && color.equals(apple.getColor())) || (!flag && weight < apple.getWeight())) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 这里就提出了一种思想 我们上面落实到了具体的业务上  这里我们往后退一步 我们考虑的是苹果
     * 需要根据苹果的某些属性(比如是否是红色的，重量是否大于150g等等) 来返回一个Boolean值 我们
     * 把它称之为谓语(就是返回一个Boolean值的函数) 让我们定义一个接口来对选择标准建模
     * <p>
     * 书写完下面的实例后 查看书中的内容 发现这个和之前所学习的策略模式非常的相似
     * 先定义一个算法族 然后定义不同的策略模式
     */

    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    /**
     * 我 们可以用ApplePredicate的多个实现代表不同的标准
     */
    public class AppleHeavyWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public class AppleGreenColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }

    }

    /**
     * 这里继续升级 怎么应用这个 就是我在定义filterApple的时候把ApplePredicate当做一参数传递进去
     * 然后你要具体的过滤什么的时候 你把它的具体的实现类传递进去 因为本身AppleGreenColorPredicate
     * 本身就是ApplePredicate的实现类  这个就是行为参数化
     * 这样就把迭代集合的逻辑和你要应用到集合每个元素的应为区分开了
     * <p>
     * 农民伯伯在调用的时候就可以filterAppleStrong(appleList,new AppleHeavyWeightPredicate())了
     * 或者filterAppleStrong(appleList,new AppleGreenColorPredicate())
     * 我理解这个过程就是一个策略模式的实现
     */
    public static List<Apple> filterAppleStrong(List<Apple> apples, ApplePredicate p) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                appleList.add(apple);
            }
        }
        return appleList;
    }

    /**
     * 那么我们还能做得更好吗？ java有一个机制是匿名类 它可以让你同时声明和实例化一个类
     * 记得不太清楚了 这个就是一个匿名类 功能就是按照一定的规则对数组进行排序
     *  Collections.sort(list, new Comparator<Student>() {
     *     @Override
     *     public int compare(Student student1, Student student2) {
     *
     *         // 这里进行比价逻辑的书写的时候  可以直接这样书写  这些书序就不用考虑两者相等的情况单独处理了
     *        if (student1.getAge() > student2.getAge()) {
     *            return 1;
     *        }
     *        return -1;
     *    }
     * });
     * 我们可以依照匿名类对上面的方法进行改造
     */

    //    List<Apple> redApple = filterAppleStrong(new ArrayList<Apple>(), new ApplePredicate() {
    //        @Override
    //        public boolean test(Apple apple) {
    //            return "red".equals(apple.getColor());
    //        }
    //    })

    /**
     * 书中说匿名类还是不够好,第一 它往往比较笨重 因为它占用的空间比较多 有一些程序员觉得这个用起来比较
     * 难以理解
     * 好的代码应该是让人一目了然的
     * 理想状态下 我们向鼓励程序员进行行为参数化的代码编写  来来来
     * 行为参数化(类、匿名类、lambda)
     */

    //    List<Apple> redApple = filterAppleStrong(new ArrayList<Apple>(), (Apple apple) ->
    //                                                      "red".equals(apple.getColor()));

    /**
     * 继续进行优化 将List类型进行抽象化
     * 这样做的目的就是 我后续可以把这个方法用到苹果啊 香蕉啊  Integer String 上
     * 下面额public static <T> 用来声明类型参数的，
     */

    public  interface Predicate2<T>{
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate2<T> p) {
        List<T> result = new ArrayList<>();
        for (T e: list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    List<Apple> apples = filter(new ArrayList<Apple>(), (Apple apple) -> "red".equals(apple.getColor()));
    List<Integer> integers = filter(new ArrayList<Integer>(), (Integer i) -> i % 2 == 0);

}
