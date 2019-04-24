package com.guessfinger.day11.moreimplements;

/**
 * create by GuessFinger on 2019/4/24
 **/
public interface B extends A{

    @Override
    default void hello() {
        System.out.println("say hello from B");
    }
}

