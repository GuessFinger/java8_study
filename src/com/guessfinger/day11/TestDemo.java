package com.guessfinger.day11;

import com.guessfinger.day03.DefultDemo;

/**
 * create by GuessFinger on 2019/4/24
 **/
public interface TestDemo {
   void test();
}

interface  TestDemo2{
    void test();
}

class ImplementsDemos implements TestDemo2, TestDemo {

    // 之前自己没有考虑过这方面的问题 这里的test()即表示TestDemo的也表示TestDemo2的test
    // 但是有一定要求 比如返回值类型相同 等等 这个在收藏夹中
    @Override
    public void test() {

    }
}