package com.bh.kotlin.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 *
 * 建议这样使用：挂起函数的组合
 *  使用async 与await进行结构化并发程序的开发
 *
 *  runBlocking 是为了计算耗时
 *
 *
 */
fun main() = runBlocking{
        val time = measureTimeMillis {
            println("${sum()}")
        }
    println("time is $time")
}

private suspend fun test10(): Int {
    delay(1000)
    return 10
}

private suspend fun test20(): Int {
    delay(1000)
    return 20
}

private suspend fun sum(): Int = coroutineScope {
    val test10 = async { test10() }
    val test20 = async { test20() }
    test10.await() + test20.await()
}