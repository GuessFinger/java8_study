package com.guessfinger.day09;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * create by GuessFinger on 2019/4/22
 **/
public class IsPrime {
    // 我们需要的判断一个数字是不是质数
    public boolean isPrimeFlag(int num) {
        return IntStream.range(2, num)
                .noneMatch(i -> num % i == 0);
    }

    // 一个简单的优化就是测试小于等于待测试数的平方根的银子
    public boolean isPrimeFlagImprove(int num) {
        int numSq = (int) Math.sqrt(num);
        return IntStream.range(2, numSq).noneMatch(i -> num % i == 0);
    }

    // 我们接下来就进行区分
    public Map<Boolean, List<Integer>> partitionByPrime(int num) {
        return IntStream.range(2, num).boxed().collect(
                Collectors.partitioningBy(cand -> isPrimeFlag(cand))
        );
    }

    public static void main(String[] args){
        System.out.println(new IsPrime().partitionByPrime(8));
    }
}


