package com.guessfinger.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * create by GuessFinger on 2019/4/9
 **/
public class DefultDemo {

    /**
     * java.util.Function.Predicate<T> 接口定义了一个名叫test的抽象方法
     * 接口泛型T 返回值是Boolean类型的 在你需要表示一个设计泛型T 以及返回值是Boolean类型的时候就可以使用这个
     */
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> t,Predicate<T> p) {
        List<T> list = new ArrayList<>();
        for (T t1: t) {
            if (p.test(t1)) {
                list.add(t1);
            }
        }
        return list;
    }

    List<String> resultList1 = filter(new ArrayList<String>(), (String s) -> "q".equals(s));
    List<Integer> integerList1 = filter(new ArrayList<Integer>(), (Integer i) -> i % 2 == 0);


    /**
     * java.util.Function.Consumer<T> 定义了一个名叫accept的方法 它接受泛型T 返回值为void 也就是没有返回值
     * 我们可以使用这个执行foreach 打印一个数组
     * 其实这个Consumer 和 Predicate 还有下面的一个都是不用定义的 这个java8自带的 你直接用就可以了
     * (T t) -> void
     */

    public static <T> void foreachList(List<T> tList,Consumer<T> c) {
        for (T t: tList) {
            c.accept(t);
        }
    }

    public static void main(String[] args){
        // 测试foreachList的lambda表达式用的
        foreachList(new ArrayList<Integer>(),(Integer i) -> System.out.println(i));

        // 测试function<T,R>
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("aaa");
        list.add("aa");
        List<String> list1 = map(list, (String s) -> s+"3");
    }

    /**
     * java.util.Function.Function<T,R> 接口定义了一个apply的方法 它接口一个泛型T的对象 并返回一个R的对象
     * 如果你要定义一个lambda表达式 将输入对象的信息映射到输出 就可以使用这个接口
     * 比如你要提取苹果的重量 或者把字符串映射为他的长度
     * 这个可以类比一下就是筛选功能
     * 注意这里输入的是T类型的 但是返回的R类型的 你用来承接的必须和你lambda后面的签名是一样的
     */

    public static <T,R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

}
