package com.guessfinger.day03;

/**
 * create by GuessFinger on 2019/4/10
 **/
public class A {
    public void a() {
        System.out.println("aaa");
    }
}

class B {
    public static A getA() {
        return new A() {
            @Override
            public void a() {
                System.out.println("bbb");
            }
        };
    }
}
