package com.guessfinger.day13;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * create by GuessFinger on 2019/4/28
 **/
public class TimeDemo3 {
    public static void main(String[] args){
        // 如果我们现在已经有两个一个LocalDate对象 我们想要创建一个他的修改版本 最简单的方法
        // 就是使用withAttribute方法  这个方法会创建一个对象的副本 注意我们前面说过 之前穿件
        // 的时间对象都是不可变的 所以使用这个方法后 都是新创建一个对象 不会修改原来的对象
        LocalDate localDate = LocalDate.of(2019, 5, 12);
        LocalDate localDate1 = localDate.withYear(2020);
        LocalDate localDate2 = localDate1.withDayOfMonth(6);
        LocalDate localDate4 = localDate2.with(ChronoField.DAY_OF_MONTH,5);
        System.out.println(localDate4);
        System.out.println(ChronoUnit.MINUTES);

        // 我们以相对的方式修改LocalDate对象的属性
        LocalDate localDate3 = LocalDate.of(2020, 5, 5);
        LocalDate localDate5 = localDate3.plusWeeks(2);
        LocalDate localDate6 = localDate5.minusYears(2);
        LocalDate localDate7 = localDate6.plus(6, ChronoUnit.MONTHS);
    }
}
