package com.guessfinger.day05;

/**
 * @Author GuessFinger
 * create by GuessFinger on 2019/4/16
 **/
public class Dish {
    private String name;
    private boolean vegetarian;
    private Integer calories;
    private Double price;
    private Type type;

    public Dish() {}

    public Dish(String name, boolean vegetarian, Integer calories, Double price, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {MEAT, FISH, OTHER,}
}
