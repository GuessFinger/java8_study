package com.guessfinger.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * create by GuessFinger on 2019/4/9
 **/
public class LambdaTest {


    public LambdaTest() throws IOException {
    }

    /**
     * 说明一下 下面代码中的BufferedReader 做有用代码的工作
     * 其中在使用java7中的try 不用显示的关闭资源了
     */
    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    /**
     * 上面的代码是有局限性的 你只能读取到文件第一行内容 如果你要读取两行内容 甚至是返回最频发的词
     * 这个是不满足要求的那么该怎么办呢？
     * 理想状态下 你要重用执行设置和清理代码
     * 主要的理念就是 行为参数化 需要告诉processFile你需要执行什么操作
     */


    // 第一步：记得行为参数化
    // String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());


    /**
     * 我们前面有说过 lambda只能用于上下文是函数式接口的情况 所以我要创建一个 BufferedReader -> String
     * 还可以进行抛出异常
     */
    // 第二步：使用函数式接口来传递行为
    public interface BufferedReaderProcess {
        String process(BufferedReader b) throws IOException;
    }
    // 现在就可以把这个接口作为processFile()方法额参数了
    public static String processFile(BufferedReaderProcess b) throws IOException{
        return "不是完全体";
    }
    // 第三部：执行一个行为
    // 任何的BufferedReader -> String 形式的lambda表达式都可以做process()的参数
    // 因为它符合 BufferedReaderProcess 接口中Process中定义的签名
    public static String processFileStrong(BufferedReaderProcess b)throws  IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            return b.process(bufferedReader);
        }
    }

    // 最红的完全体
    String result = processFileStrong((BufferedReader br) -> br.readLine() + br.readLine());
    String oneLine = processFileStrong((BufferedReader b) -> b.readLine());
    // 匿名函数的写法
    String oneLine2 = processFileStrong(new BufferedReaderProcess(){

        @Override
        public String process(BufferedReader b) throws IOException {
            return b.readLine();
        }
    });

    // 这里可能理解的还不是很透彻 一会重新把第2天的内容进行一个查看


}
