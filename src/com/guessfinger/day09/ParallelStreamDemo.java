package com.guessfinger.day09;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * create by GuessFinger on 2019/4/22
 **/
public class ParallelStreamDemo {
    // 我们这个类主要就是为了说明并行流的处理优势 通过的例子就是求前若干个数字之和

    // 方法1
    public static Long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    // 方法2 用更传统的方法
    public static Long iteratorSum(long n) {
        long result = 0;
        for (long i = 0; i< n; i ++) {
            result += i;
        }
        return result;
    }

    /**
     *  我们这里把顺序流转为并行流
     *  这里需要说明的是 对顺序流调用parallel并不意味着流本身有什么实质性的变化 它内部实际上就是设了一个
     *  boolean标志 表示你想让调用parallel之后进行的所有的操作都是并行处理
     *  类似的 你只要对并行流调用sequential方法就可以把它变成顺序流 你可以把它们结合起来 这样就可以认为的
     *  控制哪些是需要并行处理 哪些需要顺序处理
     */
    public static Long parallelSum(long n) {
        return Stream.iterate(0L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 测量流性能
     */

    public static long measureSum(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10 ;i ++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            // 下面的写法我以为是错误的 查阅百度以后发现这个是java7的特性
            // 为了就是放置你在写数字的时候 多一个0或者少一个0 不容易发现
            // 这个有一定的显示 _ 只能用在数字之间 不能把一个数字用_开头 或者用_结尾
            // 还有一些其他的限制在收藏夹中
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result:" + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    // 这里我们使用LongStream
    public static long rangedSum(long num) {
        return LongStream.rangeClosed(1, num).reduce(0L,Long::sum);
    }

    // 如果我们这里在上面的基础上采用并行流呢？
    public static long parallelRangeSun(long num) {
        return LongStream.rangeClosed(1, num).parallel().reduce(0L, Long::sum);
    }

    public static void main(String[] args){
        // 这个用的就是最上面的顺序流处理的 198
        System.out.println("sequential sum done in " + ParallelStreamDemo.measureSum(
                ParallelStreamDemo::sequentialSum,10_000_000) + "msecs");
        // iterator 来进行处理 11
        System.out.println("iterator sum done in " + ParallelStreamDemo.measureSum(
                ParallelStreamDemo::iteratorSum,10_000_000) + "msecs");
        // 我们这里用并行版本 256
        System.out.println("parallel sum done in " + ParallelStreamDemo.measureSum(
                ParallelStreamDemo::parallelSum, 10_000_000) + "msecs");
        // 从上面的结果来看 并行处理的结果好像不是那么好 中间有两个问题、
        // iterate生成的是装箱对象 必须拆箱成数字才能进行求和
        // 我们很难把iterate分成很多的独立块来执行因为每次应用这个函数都要依赖前一次应用的结果

        // 这里面就说明一个问题 大数据运算的时候一定要考虑拆箱装箱 这个也非常的消耗性能
        // 我们在之前学习过一个叫做LongStream.rangeClosed的方法
        // 这个方法直接产生原始类型long 没有拆箱和装箱 它生成的数字范围很容易被拆分成小块

        // 这里我们用LongStream.rangeClosed 这个更快 8
        System.out.println("rangedSum sum done in " + ParallelStreamDemo.measureSum(
                ParallelStreamDemo::rangedSum,10_000_000) + "msecs");

        // 在上面的基础上我们采用并行流 这个更快 4
        System.out.println("parallel sum done in " + ParallelStreamDemo.measureSum(
                ParallelStreamDemo::parallelRangeSun,10_000_000) + "msecs");

        // 并行化处理不是没有代价的 主要的代价是在多个内核之间移动数据的代价可能比你想象中的要大
        // 所以很重要的一点是要保证在内核中并行执行工作的时间比在内核之间传输数据的时间长。
        //  forEach 中调用的方法有副作用，它会改变多个线程共享的对象的可变状态。也就是你采用这种方法
        // 结果有可能是不一样的
    }


}
