package com.bh.kotlin.vals

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding




@ConfigurationProperties(prefix = "person")
@ConstructorBinding
data class ValTest(
    val name: String,
    val age: Int
)