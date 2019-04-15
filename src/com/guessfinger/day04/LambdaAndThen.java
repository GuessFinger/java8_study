package com.guessfinger.day04;

import java.util.function.Function;

/**
 * create by GuessFinger on 2019/4/15
 **/
public class LambdaAndThen {
    // 我们现在可以用Function的符合函数 下面第一行就是函数的额引用的写法
    Function<String, String> f1 = Letter::addHeader;
    Function<String, String> finalString = f1.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

}

class Letter{
    public static String addHeader(String text) {
        return "亲爱的召唤师" + text;
    }

    public static String addFooter(String text) {
        return "此致敬礼" + text;
    }

    public static String checkSpelling(String text) {
        return text.replace("擦", "口吐芬芳");
    }
}