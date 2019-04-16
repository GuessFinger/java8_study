package com.guessfinger.day05;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * create by GuessFinger on 2019/4/16
 **/
public class IoTest {
    public static void main(String[] args){
        try {
            File file = new File("e:/000.txt");
            FileInputStream inputStream = new FileInputStream(file);
            int num = 0;
            byte[] array = new byte[1024];
            while ((num = inputStream.read(array)) != -1) {
                System.out.println(new String(array,0,num));
            }
            num = 0;
            while ((num = inputStream.read(array)) != -1) {
                System.out.println(new String(array,0,num));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> title = Arrays.asList("java8", "in", "action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }
}
