package com.guessfinger.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * create by GuessFinger on 2019/4/24
 **/
public class InterfaceDemo {
    public static void main(String[] args){
        List<Integer> newList = Arrays.asList(1,3,4,5,65,3,2,-1);
        // 下面的newList.sort() 就是使用list的默认方法
        // 里面的就是  Comparator的一个全新的静态方法 按照自然顺序对其中的元素排序
        newList.sort(Comparator.naturalOrder());
    }
}
