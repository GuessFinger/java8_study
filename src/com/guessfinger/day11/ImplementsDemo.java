package com.guessfinger.day11;

/**
 * create by GuessFinger on 2019/4/24
 **/
public class ImplementsDemo implements TestDemo {
    @Override
    public void test() {
        System.out.println(1);
    }
    public static void main(String[] args){
        new ImplementsDemo().test();
    }
}
