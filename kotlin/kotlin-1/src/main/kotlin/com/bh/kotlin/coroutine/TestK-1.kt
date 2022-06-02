package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 延迟
 *
 *  关于async的延迟执行
 *  我们可以通过将async方法的start参数设置为CoroutineStart.LAZY 来实现协程的延迟执行
 *  在这种情况下，协程会在两种场景下去执行：调用deferred的await方法，或是调用Job的start方法
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        coroutineScope {
            val test10 =async(start=CoroutineStart.LAZY){ test10()}

            val test20 = async(start=CoroutineStart.LAZY){test20()}

            //显示启动两个协程
            test10.start()
            test20.start()

            println("${(test10.await()+test20.await())}")
        }
    }
    //1026
    println("$time")
}

private suspend fun test10(): Int = coroutineScope {
    delay(1000)
    10
}

private suspend fun test20(): Int = coroutineScope {
    delay(1000)
    20
}