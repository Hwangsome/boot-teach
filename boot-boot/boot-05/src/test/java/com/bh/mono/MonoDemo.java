package com.bh.mono;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MonoDemo {


    @Test
    public void test1() {
        /**
         * 就目前而言，要了解的是，虽然响应式的例子似乎仍然遵循一步一步的模式，
         * 但是这确实是一个用于数据流的管道。在管道的每个阶段，数据被以某种方式修改了，
         * 但是不能知道哪一步操作被哪一个线程执行了的。它们可能在同一个线程也可能不是。
         */
        Mono.just("Craig")
                .map(n -> n.toUpperCase())
                .map(cn -> "Hello, " + cn + "!")
                .subscribe(System.out::println);
    }

    /**
     * mergeWith
     *
     * 21:03:39.610 [parallel-2] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:0
     * 21:03:40.611 [parallel-2] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:1
     * 21:03:40.663 [parallel-5] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:0
     * 21:03:41.610 [parallel-2] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:2
     * 21:03:41.668 [parallel-7] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:1
     * 21:03:42.673 [parallel-9] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:2
     *
     * 可以发现，他们是交叉合并的。
     */
    @Test
    public void test2() {
        Flux<String> flux1 = Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .map(e -> "[flux1]:"+e);

        Flux<String> mergeFlux = Flux.interval(Duration.ofSeconds(1))
                .delayElements(Duration.ofSeconds(1))
                .take(3)
                .map(e -> "[flux2]:"+e)
                .mergeWith(flux1);

        mergeFlux.subscribe(e -> {
            log.info("subscribe:{}",e);
        });

        mergeFlux.blockLast();
    }

    /**
     * 21:06:44.228 [parallel-3] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:0
     * 21:06:45.236 [parallel-6] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:1
     * 21:06:46.238 [parallel-8] INFO com.bh.mono.MonoDemo - subscribe:[flux2]:2
     * 21:06:47.240 [parallel-10] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:0
     * 21:06:48.243 [parallel-10] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:1
     * 21:06:49.243 [parallel-10] INFO com.bh.mono.MonoDemo - subscribe:[flux1]:2
     *
     * 可以发现concatWith只是连接两个flux的数据，并不是按emit的顺序交叉来
     */
    @Test
    public void testConcat(){
        Flux<String> flux1 = Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .map(e -> "[flux1]:"+e);

        Flux<String> concatFlux = Flux.interval(Duration.ofSeconds(1))
                .delayElements(Duration.ofSeconds(1))
                .take(3)
                .map(e -> "[flux2]:"+e)
                .concatWith(flux1);
        concatFlux.subscribe(e -> {
            log.info("subscribe:{}",e);
        });
        concatFlux.blockLast();
    }

    /**
     * 21:08:27.870 [main] INFO com.bh.mono.MonoDemo - subscribe:[a,1]
     * 21:08:27.872 [main] INFO com.bh.mono.MonoDemo - subscribe:[b,2]
     * 21:08:27.872 [main] INFO com.bh.mono.MonoDemo - subscribe:[c,3]
     * 21:08:27.872 [main] INFO com.bh.mono.MonoDemo - subscribe:[d,4]
     * 21:08:27.872 [main] INFO com.bh.mono.MonoDemo - subscribe:[e,5]
     * 21:08:27.872 [main] INFO com.bh.mono.MonoDemo - subscribe:[a,0]
     *
     * 可以发现flux1相比flux2多余的数据没有被zip
     */
    @Test
    public void testZip(){
        List<String> firstList = Lists.newArrayList("a","b","c","d","e","a","b");
        List<String> secondList = Lists.newArrayList("1","2","3","4","5","0");
        Flux<Tuple2<String,String>> zipFlux =  Flux.fromIterable(firstList)
                .zipWith(Flux.fromIterable(secondList));
        zipFlux.subscribe(e -> {
            log.info("subscribe:{}",e);
        });
    }

    /**
     * flatMap是异步的
     * 21:10:40.368 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:1
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:2
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:2
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:3
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:3
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:3
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:4
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:4
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:5
     * 21:10:40.381 [parallel-1] INFO com.bh.mono.MonoDemo - subscribe:5
     * 21:10:40.381 [parallel-10] INFO com.bh.mono.MonoDemo - subscribe:2
     * 21:10:40.382 [parallel-11] INFO com.bh.mono.MonoDemo - subscribe:4
     * 21:10:40.382 [parallel-11] INFO com.bh.mono.MonoDemo - subscribe:5
     * 21:10:40.382 [parallel-3] INFO com.bh.mono.MonoDemo - subscribe:1
     * 21:10:40.384 [parallel-10] INFO com.bh.mono.MonoDemo - subscribe:1
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:1
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:2
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:3
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:4
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:5
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:1
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:2
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:3
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:4
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:5
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:1
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:2
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:3
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:4
     * 21:10:40.386 [main] INFO com.bh.mono.MonoDemo - map subscribe:5
     */
    @Test
    public void testFlatMap(){
        List<String> secondList = Lists.newArrayList("1","2","3","4","5");
        Flux<String> flatMapFlux = Flux.fromIterable(secondList)
                .flatMap((str) ->{
                    return Mono.just(str).repeat(2).map(String::toUpperCase).delayElements(Duration.ofMillis(1));
                });
        flatMapFlux.subscribe(e -> {
            log.info("subscribe:{}",e);
        });
        flatMapFlux.blockLast();

        Flux<String> mapFlux = Flux.fromIterable(secondList)
                .repeat(2)
                .map(String::toUpperCase);
        mapFlux.subscribe(e -> {
            log.info("map subscribe:{}",e);
        });
        mapFlux.blockLast();
    }

    /**
     * 21:14:25.763 [main] INFO com.bh.mono.MonoDemo - subscribe:15
     */
    @Test
    public void testReduce(){
        List<String> secondList = Lists.newArrayList("1","2","3","4","5");
        Mono<Integer> reduceMono = Flux.fromIterable(secondList)
                .flatMap(e -> Mono.just(e).map(item -> Integer.valueOf(item)))
                .reduce((total, e) -> total + e);
        reduceMono.subscribe(e -> {
            log.info("subscribe:{}",e);
        });
    }

    /**
     * 21:15:05.479 [main] INFO com.bh.mono.MonoDemo - item:[A, A]
     * 21:15:05.479 [main] INFO com.bh.mono.MonoDemo - item:[B, B]
     * 21:15:05.479 [main] INFO com.bh.mono.MonoDemo - item:[C]
     * 21:15:05.479 [main] INFO com.bh.mono.MonoDemo - item:[D]
     * 21:15:05.479 [main] INFO com.bh.mono.MonoDemo - item:[E]
     */
    @Test
    public void testGroup(){
        List<String> firstList = Lists.newArrayList("a","b","c","d","e","a","b");
        Flux<GroupedFlux<String, String>> groupFlux = Flux.fromIterable(firstList)
                .map(String::toUpperCase)
                .groupBy(key -> key);
        groupFlux.subscribe(e -> {
            log.info("subscribe:{}",e.collectList().subscribe(item -> {
                log.info("item:{}",item);
            }));
        });
    }

    /**
     * 21:16:41.615 [main] INFO com.bh.mono.MonoDemo - subscribe:1
     * 21:16:41.616 [main] INFO com.bh.mono.MonoDemo - subscribe:2
     */
    @Test
    public void testFirst(){
        List<String> firstList = Lists.newArrayList("a","b","c","d","e","a","b");
        List<String> secondList = Lists.newArrayList("1","2","3","4","5");
        Flux<String> firstFlux = Flux.fromIterable(firstList)
                .delayElements(Duration.ofMillis(200));
        Flux<String> secondFlux = Flux.fromIterable(secondList)
                .take(2);

        Flux<String> result = Flux.first(firstFlux, secondFlux);
        result.subscribe(e -> {
            log.info("subscribe:{}",e);
        });
    }

    /**
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - A
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - B
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - C
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - D
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - E
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - A
     * 21:17:05.216 [main] INFO com.bh.mono.MonoDemo - B
     */
    @Test
    public void testToIterable(){
        List<String> firstList = Lists.newArrayList("a","b","c","d","e","a","b");
        Iterable<String> itr = Flux.fromIterable(firstList)
                .map(String::toUpperCase)
                .toIterable();
        itr.forEach(e -> log.info(e));
    }

}
