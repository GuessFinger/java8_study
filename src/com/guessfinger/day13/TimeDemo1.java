package com.guessfinger.day13;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * create by GuessFinger on 2019/4/28
 **/
public class TimeDemo1 {
    public static void main(String[] args){

        // 1.使用LocalDate 和 LocateTime
        // LocalData 该实例是一个不可变对象 它只提供简单的日期 并不含当天的时间日期
        // 这个的意思就是今天的几点几分之类的 另外它也带任何和时区相关的信息
        // 我们用静态工厂方法的of创建一个LocalDate实例
        LocalDate date = LocalDate.of(2019, 4, 5);
        // 获取年份
        int year = date.getYear();
        System.out.println(year);
        // 获取月份 注意这里的对象是Month对象
        Month month = date.getMonth();
        System.out.println(month);
        // 获取具体的day
        int day = date.getDayOfMonth();
        System.out.println(day);
        // 获取星期
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        // 获取这个星期有几天
        int dayOfLength = date.lengthOfMonth();
        System.out.println(dayOfLength);
        // 获取年份是不是闰年
        boolean leap = date.isLeapYear();
        System.out.println(leap);
        // 我们还可以利用工厂方法从系统时钟中获取当前的日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        // 我们可以通过 date.get(ChronoField.YEAR)等方式进行数据的读取 和上面的效果是一样的
        int year1 = date.get(ChronoField.YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(year1);

        // 相应的我们可以使用 LocalTime 来创建时分秒对象
        LocalTime localTime = LocalTime.of(2, 3, 4);
        System.out.println(localTime);
        int hour = localTime.getHour();
        System.out.println(hour);
        int minute = localTime.getMinute();
        System.out.println(minute);
        int seconds = localTime.getSecond();
        System.out.println(seconds);

        // LocalDate 和 LocalTime 都可以通过解析代表他们的字符串创建 使用静态方法parse 你可以实现这个目的
        // 通过下面的实例 可以发现 如果要中这种方法进行解析的话 必须符合 2019-04-08 这种写法
        // 日期写法不符合要求的 或者不是日期的都会产生异常
        try {
            LocalDate localDate1 = LocalDate.parse("2019-4-08");
            LocalDate localDate11 = LocalDate.parse("2019-04-08");
            System.out.println(localDate1);
            LocalDate localDate2 = LocalDate.parse("2019-04-88");
            System.out.println(localDate2);
            LocalDate localDate3 = LocalDate.parse("xxx-xxx-xx");
            System.out.println(localDate3);
            System.out.println(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 接下来我们使用复合类 LocalDateTime  是LocalDate 和 LocalTime 和复合类 它同时表示
        // 日期 和 时间
        LocalDateTime localDateTime = LocalDateTime.of(2019, 5, 1, 13, 25, 55);
        System.out.println(localDateTime);
        // 我们同样也有这种写法
        LocalDateTime localDateTime1 = LocalDateTime.of(date, localTime);
        System.out.println(localDateTime1);
        // 还有下面的几种写法 这里需要说明的是 他们打印出来的结果是2019-04-05T02:03:04
        // 注意到了中间是有一个T 的 我想这个歌应该表示的是Time的意思
        LocalDateTime localDateTime2 = date.atTime(localTime);
        LocalDateTime localDateTime3 = date.atTime(13, 45, 45);
        LocalDateTime localDateTime4 = localTime.atDate(date);
        System.out.println(localDateTime4);
        // 我们可以把LocalDate 和 LocalTime 进行整合 同样的我们可以把
        // LocalDateTime 分解成LocalDate 和 LocalTime
        LocalDate localDate1 = localDateTime4.toLocalDate();
        LocalTime localTime1 = localDateTime4.toLocalTime();
        System.out.println(localDate1 + "---" + localTime1);

    }
}
