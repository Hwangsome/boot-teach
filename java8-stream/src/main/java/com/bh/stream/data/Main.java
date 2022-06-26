package com.bh.stream.data;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /**
         * 集合的流式操作：java8的一个新特性
         * 流式操作不是一个数据结构：不负责任何的数据存储
         * 更像一个迭代器，有序的获取到数据源中的每一个数据，并且可以对这些数据进行一些操作
         * 流式操作的每一个方法，返回值都是流本身
         *
         * 三个步骤：
         * 1. 获取数据源：集合，数组
         * 2. 对数据进行处理过程：过滤，排序，映射。。。（中间操作）
         * 3. 对流中的数据的整合。转成集合，数量。。。（最终操作）
         *
         * 为啥叫最终操作？
         * 执行完最终操作 流就会被关闭，不能再被使用。
         */

        //1. 获取数据源
        Stream<Person> stream = Data.getData().stream();

        //最终操作1. collect。 Collectors.toList() 转成list
        stream.collect(Collectors.toList()).forEach(System.out::println);

        // 转成set
        stream.collect(Collectors.toSet()).forEach(System.out::println);

        // 转成map
        Map<Integer, String> map = stream.collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(map);

        /**
         * reduce 聚合操作
         */
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Integer result = integerStream.reduce((num1, num2) -> num1 + num2).get();
        System.out.println("result = " + result);

        Optional<Person> reduce = stream.reduce((p1, p2) -> new Person().setScore(p1.getScore() + p2.getScore()));
        System.out.println(reduce.get());

        /**
         * max min
         * 找成绩最高 或最低的
         */
        final Optional<Person> maxScore = stream.max((p1, p2) -> p1.getScore() - p2.getScore());
        System.out.println("maxScore = " + maxScore.get());

        /**
         * match
         * anymatch ,allmatch,nonmatch
         *
         * 1. 集合中是否包含成绩不及格？
         */
        final boolean bScore = stream.anyMatch(person -> person.getScore() < 60);

        /**
         * count
         * 1. 求源数据中有多少的数据量
         */
        final long count = stream.count();
        System.out.println("count = " + count);

        /**
         * foreach
         */
        stream.forEach(System.out::println);

        /**
         * 中间操作
         */
        /**
         * filter :过滤
         * 成绩大于80
         */
        stream.filter(person -> person.getScore() >= 80).forEach((System.out::println));

        /**
         * distinct
         * 去重规则：
         * 1. 先判断hashcode
         * 2. hashcode相同在判断equals
         */
        stream.distinct().forEach((System.out::println));

        /**
         * Function:指定类型参数，指定类型返回值 。
         * Function< T,  R> T表示lambda的参数，R表示lambda的返回值
         * 不及格的0分
         */
        stream.map(person -> person.getScore() >= 60 ? person.getScore() : 0);

        /**
         * 并行流
         * parallel()
         *
         */
//        stream.parallel()

        /**
         * findAny:获取流中的任意一个元素，对于串行流 ==findFirst。对于并行流 ==可能=findFirst
         * findFirst:获取流中的第一个元素
         */
        stream.findFirst();

//        stream.flatMap()
    }
}
