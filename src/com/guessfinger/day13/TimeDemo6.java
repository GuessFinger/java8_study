package com.guessfinger.day13;

import java.time.*;

/**
 * create by GuessFinger on 2019/5/6
 **/
public class TimeDemo6 {
    public static void main(String[] args){
        // 在java8中我们使用java.time.ZoneId来处理时区问题
        // 时区是按照一定的规则将区域划分成的标准时间相同的区间
        // 你可以通过调用ZoneId的getRules()来获得指定时区的规则 每个特定的ZoneId对象都由一个地区ID标识
        // 地区ID 都是 区域/城市的格式 这些地区集合的设定都由英特网编号分配机构（IANA）
        // 的时区数据库提供你可以通过Java 8的新方法 toZoneId 将一个老的时区对象转换为 ZoneId
        // ZoneId zoneId = TimeZone.getDefault().toZoneId();
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println(romeZone.getRules());
        // 我们可以将当前时间和时区结合起来
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(romeZone);
        System.out.println(zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 05, 05, 05, 05, 05);
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(romeZone);
        System.out.println(zonedDateTime1);
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime2 = instant.atZone(romeZone);
        System.out.println(zonedDateTime2);

    }
}
