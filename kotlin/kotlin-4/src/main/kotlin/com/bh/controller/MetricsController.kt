package com.bh.controller

import com.bh.common.model.R
//import com.bh.config.TracingInstrumentation
import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.MeterRegistry
//import com.bh.config.MetricsConfig
import io.micrometer.statsd.StatsdMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MetricsController @Autowired constructor(/*private val metricsConfig: MetricsConfig,*/private val registry: MeterRegistry) {

//    val tracingInstrumentation = TracingInstrumentation(registry)

    @GetMapping("/test1")
    @Timed(value = "test.testM1", description = "test metrics")
    fun testM1():R{
        return R.ok().data("metricsConfig",registry);
    }
    @GetMapping("/test2")
    @Timed(value = "test.testM2", description = "test metrics2")
    fun testM2():R{
        for (i in 1 .. 1000){
            testM1()
        }
        return R.ok().data("metricsConfig",registry);
    }

}