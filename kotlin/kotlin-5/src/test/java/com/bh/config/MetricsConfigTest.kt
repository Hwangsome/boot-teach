package com.bh.config

import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MetricsConfigTest() {

    @Test
    fun testmetricsCustomizer() {
        val simpleMeterRegistry = SimpleMeterRegistry()
//        Metrics.addRegistry(simpleMeterRegistry)
//        Metrics.counter("objects.instance").increment()
        val counter: Counter = Counter
            .builder("instance")
            .description("indicates instance count of the object")
            .tags("dev", "performance")
            .register(simpleMeterRegistry)
        counter.increment()
        println(counter.count())

        println(Clock.SYSTEM.monotonicTime())
    }
}
