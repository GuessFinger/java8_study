package com.guessfinger.day14;

import java.util.function.DoubleUnaryOperator;

/**
 * create by GuessFinger on 2019/5/7
 **/
public class KeLiHua {

    // 使用前提 我们有时候在书写代码的时候 会遇到这种请求就是 将一套单位转化成另一套单位
    // 单位的转换经常会涉及到转换因子以及基线调整因子的问题 基本上调整的方案就是
    // 乘以一个转换因子  如果有需要的话进行基线调整

    static double converter(double x, double f, double b) {
        return x * f + b;
    }
    // 这个方法的书写 有一些宽泛 也就是你每次调整的时候都需要输入这个3个参数 就算你是使用同一种转换
    // 你还是需要输入这3个参数 这样的话 你是有可能同一种单位的转换 参数有可能输入错误的
    // 不是上面的方法不可以  而是由更优的解决办法

    // 我们新的处理方法 就是把转换的因子 和调整基准线 处理成一个函数
    // 你看你这样就相当于 传入因子 和基准线 接受一个参数
    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
    public static void main(String[] args){

        // 那么你就可以这样处理了 你先定义好一些这种转换公式
        DoubleUnaryOperator converCtof = curriedConverter(9.5 / 5, 32);
        DoubleUnaryOperator converCtof1 = curriedConverter(0.5, 2);

        // 因为DoubleUnaryOperator定义了applyAsDouble  那么你就可以这样使用
        double gdp = converCtof.applyAsDouble(2);
        System.out.println(gdp);

    }

}
