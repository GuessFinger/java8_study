package com.guessfinger.day03;

import java.util.Arrays;

/**
 * create by GuessFinger on 2019/4/11
 **/
public class Sort {
    public static void main(String[] args) {
        int[] i1 = new int[]{-2, 4, 7, 8, 10};
        int[] i2 = new int[]{3, 4, 7,8, 9};
        int[] i3;
        int iMax = i1[i1.length - 1] > i2[i2.length - 1] ? i1[i1.length - 1]
                : i2[i2.length - 1];
        int iMin = i1[0] < i2[0] ? i1[0] : i2[0];
        i3 = new int[iMax - iMin + 1];
        for (int i = 0; i < (i1.length <= i2.length ? i2.length : i1.length); i++) {
            if (i < i1.length) {
                if (i3[i1[i] - iMin] == 0) {
                    i3[i1[i] - iMin] = i1[i];
                } else {
                    System.out.println(i3[i1[i] - iMin]);
                }
            }
            if (i < i2.length) {
                if (i3[i2[i] - iMin] == 0) {
                    i3[i2[i] - iMin] = i2[i];
                } else {
                    System.out.println(i3[i2[i] - iMin]);
                }
            }
        }
        System.out.println(Arrays.toString(i3));
    }
}