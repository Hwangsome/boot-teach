//package com.bh.config
//
//import io.micrometer.core.instrument.MeterRegistry
//import io.micrometer.core.instrument.config.NamingConvention
//import io.micrometer.statsd.StatsdConfig
//import io.micrometer.statsd.StatsdMeterRegistry
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.env.Environment
//import java.net.InetAddress
//
//@Configuration
//@ConditionalOnProperty("management.metrics.export.statsd.enabled", havingValue = "true")
//class MetricsConfig {
//    companion object {
//        private const val APP_KEY = "application"
//        private const val HOST_KEY = "host"
//        private const val PORT_KEY = "port"
//    }
//
//
//    @Bean
//    fun metricsCustomizer(environment: Environment): MeterRegistryCustomizer<MeterRegistry> {
//        return MeterRegistryCustomizer { registry ->
//            registry.config().commonTags(APP_KEY, environment.getProperty("info.app.name"))
//            registry.config().commonTags(
//                HOST_KEY,
//                InetAddress.getLocalHost().hostName.split(".").firstOrNull()
//            )
//            registry.config().commonTags(PORT_KEY, environment.getProperty("server.port"))
//
//            registry.config().namingConvention(NamingConvention.dot)
//        }
//    }
//
//
//
//    @Bean
//    fun statsdMeterRegistry(statsdConfig: StatsdConfig): StatsdMeterRegistry {
//        return StatsdMeterRegistry.builder(statsdConfig)
//            .nameMapper(GraphiteNameMapper(APP_KEY, HOST_KEY, PORT_KEY))
//            .build()
//    }
//
//}