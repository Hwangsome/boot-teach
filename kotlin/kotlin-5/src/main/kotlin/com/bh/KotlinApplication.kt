package com.bh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * 这里没有添加open是因为这里在pom 插件中配置了对spring配置的open
 *
 * swagger:http://localhost:8989/swagger-ui/index.html
 */
@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinApplication
fun main(args: Array<String>) {

    val runApplication = runApplication<KotlinApplication>(*args)
}