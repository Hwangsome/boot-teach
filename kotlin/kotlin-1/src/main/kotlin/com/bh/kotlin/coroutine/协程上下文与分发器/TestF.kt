package com.bh.kotlin.coroutine.协程上下文与分发器

import com.bh.kotlin.coroutine.log
import kotlinx.coroutines.*

/**
 * CoroutineName上下文元素可以让我们对协程进行命名，以便能够输出可读性较好的日志信息
 *
 */
fun main() =runBlocking<Unit>(CoroutineName("main")){
    log("hello")

    val value1=async(CoroutineName("coroutine1")){
        delay(800)
        log("coroutine1 log")
        30
    }

    val value2=async(CoroutineName("coroutine2")){
        delay(1000)
        log("coroutine2 log")
        50
    }

    val result  = value1.await() + value2.await()
}