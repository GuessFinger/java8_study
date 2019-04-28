package com.guessfinger.day13;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * create by GuessFinger on 2019/4/28
 **/
public class TimeDemo2 {
    public static void main(String[] args){

        // 作为人我们习惯 用星期几 几月几号 几点几分 这样的方式去表示时间 但是对于机器
        // 可能这样就不好理解了从计算机的角度来看，建模时间最自然的格式是表示一
        // 个持续时间段上某个点的单一大整型数基本上它是以Unix元年时间
        // （传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算
        // 我们可以使用静态方法ofEpochSecond 传递一个代表秒数的值创建一个该类的实例
        // 这个方法还可以接受第二个参数 这个单位是纳秒 1秒的10亿分之一就是1纳秒 10的-9次方
        // 下面的会创建相同的对象
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);
        // 获取当前时刻的时间戳 输出结果是2019-04-28T13:32:46.869Z
        System.out.println(Instant.now());
        // 书中强调一点Instance 设计之初是为了机器服务的 所以它无法处理我们非常容易理解的时间单位
        // 这个会产生异常
        // System.out.println(Instant.now().get(ChronoField.MONTH_OF_YEAR));

        // 我们看到的所有的类都实现了Temporal 类 下面使用duration类 处理Instance 和 LocalTime LocalDateTime
        // 这个方法就是求两个时间点之间的间隔的 对于上面的LocalDate  我们不能使用这个
        // 但是对于LocalTime 我们可以使用Duration.between()

        LocalTime localTime1 = LocalTime.of(5, 5, 1);
        LocalTime localTime2 = LocalTime.of(7, 34, 51);
        LocalDateTime localDateTime1 = LocalDateTime.of(2018, 2, 5, 12, 12, 12);
        LocalDateTime localDateTime2 = LocalDateTime.of(2019, 4, 5, 13, 13, 13);
        Instant instant1 = Instant.ofEpochSecond(1000);
        Instant instant2 = Instant.ofEpochSecond(5000);
        try {
            Duration duration1 = Duration.between(localDateTime1, localDateTime2);
            Duration duration2 = Duration.between(localTime1, localTime2);
            Duration duration3 = Duration.between(instant1, instant2);
            System.out.println(duration1);
            System.out.println(duration2);
            System.out.println(duration3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 这个是为了处理 LocalDate之间的间隔
        LocalDate localDate1 = LocalDate.of(2019, 12, 12);
        LocalDate localDate2 = LocalDate.of(2018, 12, 12);
        Period period1 = Period.between(localDate2, localDate1);
        System.out.println(period1.toTotalMonths());

        // 最后Duration 和 Period 类都提供了非常方便的工厂类 创建Duration 和 Period类
        Duration duration = Duration.ofMinutes(2);
        Duration duration1 = Duration.of(3, ChronoUnit.MINUTES);
        Period period = Period.ofDays(10);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay);

    }
}
