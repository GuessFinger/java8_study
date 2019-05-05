package com.guessfinger.day13;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * create by GuessFinger on 2019/5/5
 **/
public class TimeDemo4 {

    // 截止目前 我们学习到的日期操作都是比较直接的 什么LocalTime  LocalDate LocalDateTime Instant
    // 有时候我们需要进行一些更复杂的操作的 比如将日期调整到一个周日  下一个工作日 或者本月的最后一个月
    // 我们引入一个TemporalAdjuster对象 更加灵活的处理日期

    public static void main(String[] args){


        // 需要说明的是 下面都是创建一个新的日期
        LocalDate date = LocalDate.of(2019, 5, 1);
        // 获取第几个星期的 周几是多少号？ 下面这个表示的就是 第四个星期的星期日是几号
        LocalDate date1 = date.with(dayOfWeekInMonth(4, DayOfWeek.SUNDAY));
        System.out.println(date1);
        // 获取当前date的这个月的第一天日期
        LocalDate date2 = date.with(firstDayOfMonth());
        System.out.println(date2);
        // 下个月的第一天
        LocalDate date3 = date.with(firstDayOfNextMonth());
        System.out.println(date3);
        // 明年的第一天
        LocalDate date4 = date.with(firstDayOfNextYear());
        System.out.println(date4);
        // 今天的第一天
        LocalDate date5 = date.with(firstDayOfYear());
        System.out.println(date5);
        // 同一个月中 第一个符合星期几的日期
        LocalDate date6 = date.with(firstInMonth(DayOfWeek.MONDAY));
        System.out.println(date6);
        // 当月的最后一天
        LocalDate date7 = date.with(lastDayOfMonth());
        System.out.println(date7);
        // 今年的最后一天
        LocalDate date8 = date.with(lastDayOfYear());
        System.out.println(date8);
        // 本月最后一个符合星期几的日期
        LocalDate date9 = date.with(lastInMonth(DayOfWeek.SUNDAY));
        System.out.println(date9);
        // 下一个/上一个符合星期几的日期
        LocalDate date10 = date.with(next(DayOfWeek.SUNDAY));
        System.out.println(date10);
        LocalDate date11 = date.with(previous(DayOfWeek.SUNDAY));
        System.out.println(date11);
        // 向后调整或者向前调整第一个符合星期几的日期
        LocalDate date12 = date.with(nextOrSame(DayOfWeek.MONDAY));
        System.out.println(date12);
        LocalDate date13 = date.with(previousOrSame(DayOfWeek.MONDAY));
        System.out.println(date13);
        // 如我们看到的 我们使用TemporalAdjuster 我们可以创建更加复杂的日期操作
        // 我们同样也可以创建属于自己的TemporalAdjuster
        //  public interface TemporalAdjuster{
        //      Temporal adjustInto(Temporal temporal);
        //   }


    }
}
