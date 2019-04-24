package com.guessfinger.day11;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * create by GuessFinger on 2019/4/24
 **/
public class ExtendsDemo extends  AbstractDemo{
    @Override
    void eat() {
        System.out.println(1);
    }

    @Override
    public void talk() {
        System.out.println(2);
    }
    public static void main(String[] args){
        new ExtendsDemo().eat();
        new ExtendsDemo().talk();
        Iterator x = new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }

            @Override
            public void remove() {

            }

            @Override
            public void forEachRemaining(Consumer action) {

            }
        };
    }
}
