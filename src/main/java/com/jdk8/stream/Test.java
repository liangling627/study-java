package com.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Hank
 * @Date 2019年02月15日
 */
public class Test {
    public static void main(String[] args) {
        List<String> words = Lists.newArrayList();
        words.add("hi");
        words.add("word");
        words.add("hello");
        words.add("java");

        long count = words.stream().filter(w -> w.length() > 4).count();
        System.out.println(count);
        count = words.parallelStream().filter(w -> w.length() > 4).count();
        System.out.println(count);

//      流只能读取一次
        Stream<String> words1 = Stream.of("2", "3", "4", "6", "8", "9");
        long count1 = words1.filter(w -> Integer.parseInt(w) > 5).count();
        System.out.println(count1);
        words1.filter(w -> Integer.parseInt(w) > 5).forEach(System.out::println);

    }
}
