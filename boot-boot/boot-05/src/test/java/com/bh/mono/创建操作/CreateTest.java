package com.bh.mono.创建操作;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class CreateTest {

    /**
     * 如果想从 Flux 或是 Mono 创建一个或多个对象，可以 Flux 或 Mono 中的静态方法
     * just() 去创建一个响应式类型，其中的数据由这些对象驱动。
     * 例如，下面这个测试方法就是使用 5 个 String 对象来创建一个 Flux：
     *
     * 这样就创建了一个 Flux，但它没有订阅者。要是没有订阅者，数据不会流动。
     * 以花园软管的思路进行类比，你已经把软管接到出水口了，另一端就是从自来水公司流出的水。
     * 但是水不会流动，除非你打开水龙头。对响应式类型的订阅就是打开数据流的方式。
     *
     * subscribe() 中的 lambda 表达式实际上是 java.util.Consumer(传递一个参数，进行消费)，用于创建响应式流的 Subscriber。
     * 由于调用了 subscribe() 方法，数据开始流动了。在这个例子中，不存在中间操作，
     * 因此数据直接从 Flux 流到了 Subscriber。
     *
     * 21:25:17.099 [main] INFO com.bh.mono.创建操作.CreateTest - Here's some fruit:Apple
     * 21:25:17.104 [main] INFO com.bh.mono.创建操作.CreateTest - Here's some fruit:Orange
     * 21:25:17.104 [main] INFO com.bh.mono.创建操作.CreateTest - Here's some fruit:Grape
     * 21:25:17.104 [main] INFO com.bh.mono.创建操作.CreateTest - Here's some fruit:Banana
     * 21:25:17.104 [main] INFO com.bh.mono.创建操作.CreateTest - Here's some fruit:Strawberry
     */
    @Test
    public void createAFlux_just() {
        Flux<String> fruitFlux = Flux
                .just("Apple", "Orange", "Grape", "Banana", "Strawberry");
        fruitFlux.subscribe(
                f -> log.info("Here's some fruit:{}",f));
    }

    /**
     * 为了在运行过程中观察响应式类型，一个好方法就是将 Flux 或 Mono 打印到控制台里面。
     * 但是，测试 Flux 或 Mono 更好的方式是使用 Reactor 中的 StepVerifier。
     * 给定一个 Flux 或 Mono，StepVerifier 订阅这个响应式类型，然后对流中流动的数据应用断言，最后验证流以预期方式完成。
     *
     * 这个例子中，StepVerifier 订阅了 Flux，然后对每一个匹配到的期望的水果名字做断言。最后，它验证了 Strawberry 是由 Flux 生成的，对 Flux 的验证完毕。
     */
    @Test
    public void createAFlux_just2() {
        Flux<String> fruitFlux = Flux
                .just("Apple", "Orange", "Grape", "Banana", "Strawberry");
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    /**
     * 从集合创建
     * Flux 也可从任何的集合创建，如 Iterable 或是 Java Stream。
     * 为了从数组创建一个 Flux，调用静态方法 fromArray()，然后将数组作为数据源传入：
     */
    @Test
    public void createAFlux_fromArray() {
        String[] fruits = new String[] {
                "Apple", "Orange", "Grape", "Banana", "Strawberry" };

        Flux<String> fruitFlux = Flux.fromArray(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public void createAFlux_fromIterable() {
        List<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Orange");
        fruitList.add("Grape");
        fruitList.add("Banana");
        fruitList.add("Strawberry");
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);
        // ... verify steps
    }

    @Test
    public void createAFlux_fromStream() {
        Stream<String> fruitStream =
                Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
        Flux<String> fruitFlux = Flux.fromStream(fruitStream);
        // ... verify steps
    }
}
