package com.guessfinger.day12;

import java.util.Optional;

/**
 * create by GuessFinger on 2019/4/27
 **/
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
