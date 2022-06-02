package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 通过async与await实现高效并发
 *
 * 挂起函数的组合
 *
 * 使用async与await实现并发：
 * 从概念来说，async就像是launch一样，他会开启一个单独的协程，这个协程是个轻量级线程，可以与其他协程并发工作。区别在于，launch
 * 会返回一个job,但是Job并不会持有任何结果只，而async会返回一个Deferred，这是一个轻量级的非阻塞的future,他代表一个promise
 * 可以在稍后提供一个结果值
 *
 * 可以通过在一个deferred值上调用.await()方法来获取最终的结果值，Deferred也是哥Job,因此可以在需要时对其进行取消
 *
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        coroutineScope {
            val test10 =async{ test10()}

            val test20 = async{test20()}

            println("${(test10.await()+test20.await())}")
        }
    }
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