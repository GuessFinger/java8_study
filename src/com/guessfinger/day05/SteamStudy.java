package com.guessfinger.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by GuessFinger on 2019/4/16
 **/
public class SteamStudy {
    public static void main(String[] args) {


        // 我们的目的是 选取出能量低于400的菜品 并且按照顺序进行排列 然后的得到其中的菜品名称
        // originalList 表示现有的菜的集合 lowCaloriesList 表示低能量的菜 lowCaloriesNameList 表示的是低能量菜的名称
        Dish dish1 = new Dish("宫保鸡丁", false,500, 23.15,Dish.Type.MEAT);
        Dish dish2 = new Dish("麻辣豆腐", true,200, 13.15,Dish.Type.OTHER);
        Dish dish3 = new Dish("肉末茄子", false,300, 15.15,Dish.Type.MEAT);
        Dish dish4 = new Dish("酸辣土豆丝",true, 100, 10.10,Dish.Type.OTHER);
        Dish dish5 = new Dish("芹菜炒肉",false, 400, 18.15,Dish.Type.MEAT);
        Dish dish6 = new Dish("韭菜炒蛋",true, 388, 14.15,Dish.Type.OTHER);
        Dish dish7 = new Dish("土豆回锅肉3",false, 800, 28.15,Dish.Type.MEAT);
        Dish dish8 = new Dish("酸辣鱼",false, 1000, 58.15,Dish.Type.FISH);

        List<Dish> originalList = new ArrayList<>();
        originalList.add(dish1);
        originalList.add(dish2);
        originalList.add(dish3);
        originalList.add(dish4);
        originalList.add(dish5);
        originalList.add(dish6);
        originalList.add(dish7);
        originalList.add(dish8);

        List<Dish> lowCaloriesList = new ArrayList<>();

        // java7 中我们怎么处理
        for (Dish dish : originalList) {
            if (dish.getCalories() < 400) {
                lowCaloriesList.add(dish);
            }
        }
        List<String> lowCaloriesNameList = new ArrayList<>();
        // 这里基本有一个思想了 就是关于lambda表达式的写法
        Collections.sort(lowCaloriesList, Comparator.comparing(Dish::getCalories));
        for (Dish dish: lowCaloriesList) {
            System.out.println(dish.getName());
            lowCaloriesNameList.add(dish.getName());
        }

        // 在上面的额代码中 我们用了一个垃圾变量"lowCaloriesList" 它唯一的一个作用就是 用作一个中间变量
        // 也就是他没有实际的意义 存储一下数据 然后对数据进行取名称的操作
        // java8中是这样的
        // 当我看到这个代码的时候 我表示这个也太牛逼了吧！
        // 如果我们需要利用多核架构并执行这段代码 我们只要把stream() 换成parallelStream()就可以了
        // 这个后面会进行学习的
        List<String> lowCaloriesNameList2 = originalList.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        // 1 让开发者读起来代码是非常的简洁的 2 表述的意思是非常明显的 3 还有一点就是它处理线程这一块
        // 后面会进行学习的

        // 获取每个菜名称的长度
        originalList.stream()
                    .map(r -> r.getName().length())
                    .forEach(System.out::println);

    }
}
