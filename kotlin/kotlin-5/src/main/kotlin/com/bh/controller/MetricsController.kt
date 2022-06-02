package com.bh.controller

import com.bh.common.model.R
//import com.bh.config.MetricsConfig
import io.micrometer.statsd.StatsdMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MetricsController @Autowired constructor(/*private val metricsConfig: MetricsConfig,*/private val statsdMeterRegistry: StatsdMeterRegistry) {

    @GetMapping("/test1")
    fun testM1():R{
        return R.ok().data("metricsConfig",statsdMeterRegistry);
    }
}