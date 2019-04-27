package com.guessfinger.day12;

import java.util.Optional;

/**
 * create by GuessFinger on 2019/4/27
 **/
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
