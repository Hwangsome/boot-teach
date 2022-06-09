package com.bh.kotlin.list;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream.of(100, 200, 300, 400, 500)
                .mapToLong(e -> e * 10)
                .filter(e -> e > 2000)
                .forEach(System.out::println);
        int [] nums = {1,2,3};
        Arrays.stream(nums)
                .mapToLong(e -> e * 10)
                .filter(e -> e > 20)
                .forEach(System.out::println);
    }
}
