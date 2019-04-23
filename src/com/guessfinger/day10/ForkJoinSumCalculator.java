package com.guessfinger.day10;

import java.io.FileInputStream;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * create by GuessFinger on 2019/4/23
 **/
public class ForkJoinSumCalculator extends RecursiveTask<Long> {


    /**
     * 要求和的数组 子任务处理的数组的起始和结束位置
     */
    private final long[] numbers;
    private final int start;
    private final int end;

    // 不再将任务分解为子任务数组的大小分界
    private static final long THERSHOLD = 10_000;

    // 公共构造函数用于创建主任务
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    // 私有构造函数用于以递归的方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        // 如果小于等于阙值 顺序计算结果
        if (length <= THERSHOLD) {
          return   computeSequentially();
        }
        // 创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        // 创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务 有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果 如果尚未完成就等待
        Long leftResult = leftTask.join();
        return rightResult + leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i< end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args){
        System.out.println(forkJoinSum(2));
    }
}


