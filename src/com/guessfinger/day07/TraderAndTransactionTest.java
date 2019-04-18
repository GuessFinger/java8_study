package com.guessfinger.day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author : Mx
 * @Description:
 * @Date: Created in 9:30 2019/4/18
 */
public class TraderAndTransactionTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1.找出2011年发生的所有交易 并按交易额排序
        // 先准成stream流 然后用filter过滤 接着中sorted进行排序 里面用静态辅助方法Comparator.comparing()
        // 如果要倒叙排列的话用 reversed() 然后取值
        transactions.stream()
                .filter(r -> r.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getValue).collect(Collectors.toList()).stream().forEach(System.out::println);

        System.out.println("------分割线------");

        // 2.交易员都在哪些不同的城市工作过
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);
        // 改进版
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());



        System.out.println("------分割线------");

        // 3.查找所有来自剑桥的交易员 并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList())
                .stream()
                .forEach(r -> System.out.println(r.getName()));


        System.out.println("------分割线------");
        // 4.返回所有姓名字符串，按字母顺序排列
        transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);

        System.out.println("------分割线------");
        // 5.有没有交易员是在米兰工作的？

        Boolean isMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println(isMilan);

        System.out.println("------分割线------");

        // 6.打印生活在剑桥的交易员的所有交易额

         Optional<Integer> sum = transactions.stream()
                .filter(trader -> trader.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum);

        System.out.println("------分割线------");
        // 7.所有交易中交易额最高的是多少 当然还需要找到最小的交易额
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(max);
        System.out.println(min);
    }
}
