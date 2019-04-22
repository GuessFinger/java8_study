package com.guessfinger.day09;

import com.guessfinger.day05.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;

/**
 * create by GuessFinger on 2019/4/22
 **/
public class GroupIngDemo {
    public static void main(String[] args){
        Map<Boolean, Dish> mostCaloriesPartitionByVegetarian = new ArrayList<Dish>().
                stream().collect(
                partitioningBy(Dish::isVegetarian, collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                         Optional::get
                )));
    }
}
