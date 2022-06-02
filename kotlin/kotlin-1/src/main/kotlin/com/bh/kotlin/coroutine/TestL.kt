package com.bh.kotlin.coroutine

import arrow.fx.IO.Companion.async
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

/**
 * 异步风格的函数
 *
 */
fun main(){


    val time = measureTimeMillis {
        val test10Async = test10Async()
        val test20Async = test20Async()


        /**
         * await() 是挂起函数，需要运行在协程中
         *
         * 想要获取异步返回的结果，需要通过协程的方式去获取结果
         */
        runBlocking{
            val result = test10Async.await() + test20Async.await()
            println(result)
        }


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


/**
 * 下面的两个方法就是普通方法，可以不在协程中运行了，直接在main方法中运行
 *
 * 将async()函数的返回值赋值给test10Async()函数
 */
private fun test10Async() = GlobalScope.async {
    test10()
}

private fun test20Async() = GlobalScope.async {
    test20()
}

