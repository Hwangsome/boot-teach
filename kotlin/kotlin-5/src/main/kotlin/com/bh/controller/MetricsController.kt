package com.bh.controller

import com.bh.common.model.R
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import io.micrometer.statsd.StatsdMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
class MetricsController @Autowired constructor(
    /*private val metricsConfig: MetricsConfig,*/
    private val statsdMeterRegistry: StatsdMeterRegistry,
    private val meterRegistry: MeterRegistry
) {

    /**
     * http://localhost:8989/metrics/request.times.order
     * http://localhost:8989/metrics
     */
    @GetMapping("/test1")
    fun testM1(): R {
//        val simpleMeterRegistry = SimpleMeterRegistry()
//        // 写法1
//        val counter1 = simpleMeterRegistry.counter("request.times.test", "country", "china")
//        // 写法2
//        val counter2 = Counter.builder("request.times.test")
//            .tags("country", "China")
//            .register(simpleMeterRegistry)
//        counter1.increment()
//        counter2.increment()

        /**
         {
         "name": "request.times.order",
         "description": null,
         "baseUnit": null,
         "measurements": [
         {
         "statistic": "COUNT",
         "value": 3
         }
         ],
         "availableTags": [
         {
         "tag": "country",
         "values": [
         "china"
         ]
         }
         ]
         }
         */
        meterRegistry
            .counter("request.times.order", "country", "china")
            .increment()

        return R.ok().data("metricsConfig", statsdMeterRegistry)
    }

    @GetMapping("/test2")
    fun testTimer(): ResponseEntity<String> {
//        val timer = Timer
//            .builder("my.timer")
//            .description("a description of what this timer does") // 可选
//            .tags("region", "test") // 可选
//            .register(meterRegistry)

        val timer = Timer.start(meterRegistry)
        // 一些业务逻辑
        TimeUnit.SECONDS.sleep(2)
        timer.stop(meterRegistry.timer("my.timer", "region", "test"))

        //meterRegistry.timer("request.time.order", "user", "requesttime").record(2, TimeUnit.SECONDS)
        return ResponseEntity.ok("")
    }
}
