package com.guessfinger.day13;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * create by GuessFinger on 2019/5/5
 **/
public class NextWorkingDay implements TemporalAdjuster{

    // 设计一个你自己的TemporalAdjuster实现类 能够计算明天的日期 同时过滤掉周六合周日
    // date = date.with(new NextWorkingDay());

    // 如果当前的星期是介于星期一和星期五之间那么向后推移一天 如果当天是星期六 或者是星期日
    // 则返回星期一

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 处理的方法应该是先判断当前的日期是星期几 然后根据现有的星期然后进行逻辑处理
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    /**
     * 因为TemporalAdjuster是一个函数式接口 我们可以用Lambda的方式进行处理
     *  date = date.with(temporal -> {
     *    DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
     *    int dayToAdd = 1;
     *    if (dayOfWeek == DayOfWeek.FRIDAY) {
     *      dayToAdd = 3;
     *    } else if (dayOfWeek == DayOfWeek.SATURDAY) {
     *      dayToAdd = 2;
     *    }
     *    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
     *  })
     *
     */

    // 如果想要把它变成一个工具类 那么你就把这个定义成一个类 就像上面的方式
    // 同样如果你想要用Lambda的方式的话 像上面的方式也可以
    // 还有一种方式就是使用TemporalAdjuster的静态工厂方法ofDateAdjuster()中间就是写上面的Lambda表达式


}
