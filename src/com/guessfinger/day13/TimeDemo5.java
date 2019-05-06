package com.guessfinger.day13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * create by GuessFinger on 2019/5/5
 **/
public class TimeDemo5 {
    public static void main(String[] args){
        // 现在的需求就是 我们可能根据不同的业务需求 将现有的日期处理成不同的格式 然后进行打印
        // 最重要的就是日期的解析和格式化处理
        // 在java8中我们引入了一个类java.time.format类 主要就是为了处理日期的转换等等问题
        // 这个类中最重要的是DateTimeFormatter
        LocalDate localDate = LocalDate.of(2019, 5, 5);
        System.out.println(localDate);
        // 打印结果20190505
        String s1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        // 打印结果2019-05-05
        String s2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);
        // 你同样也可以解析字符串 如果你需要在后面添加DateTimeFormatter 那么后面的选择需要和前面的字符串
        // 进行对应起来 如果用BASIC_ISO_DATE 那么前面的字符串就是没有中间的-
        // 相反的就是有的 如果他们之间弄反了 会报错的
        // 还有就是你直接使用LocalDate.parse("") 解析的时候中间的字符串必须是有 - 的 而且必须满足是8位的
        // 但是使用之前的SimpleDateFormatter 解析带- 的 可以 解析2019-5-5 的字符串
        // 但是上面的就不可以 这个一定要注意一下 不过也不太可能 如果不带0的话 不太好区分
        LocalDate localDate1 = LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate1);
        LocalDate localDate2 = LocalDate.parse("20190505", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate2);
        LocalDate localDate3 = LocalDate.parse("2019-05-05");
        System.out.println(localDate3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse("2019-5-5");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 和老的java.util.Formatter 相比较 所有的DateTimeFormatter 都是线程安全的
        // 至于为什么java.util.Formatter是线程 不安全的 主要是因为其中的calender 变量这里进行共享了
        // 至于为什么？在收藏夹中

        // 按照某个模式创建DateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = localDate.format(dateTimeFormatter);
        System.out.println(s3);
        // 这里虽然我们用dd/MM/yyyy的方式进行解析 但是解析出来的数据依旧是2019-05-05
        LocalDate localDate4 = LocalDate.parse(s3, dateTimeFormatter);
        System.out.println(localDate4);

        // 创建一个本地化的DateTimeFormatter 前面是你需要的格式 后面选择你要格式成哪个地方的日期
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.CHINA);
        String s4 = localDate4.format(dateTimeFormatter1);
        System.out.println(s4);
        LocalDate localDate5 = LocalDate.parse(s4, dateTimeFormatter1);
        System.out.println(localDate5);


    }
}
