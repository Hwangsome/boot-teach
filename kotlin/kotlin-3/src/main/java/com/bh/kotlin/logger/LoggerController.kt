package com.bh.kotlin.logger

import com.bh.common.model.R
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoggerController {
    //kotlin的日志记录
    companion object : KLogging()

    @GetMapping("/hello")
    fun helloKotlin(): R {
        logger.info { "hello" }
        return R.ok().data("data","hello springboot kotlin")
    }
}