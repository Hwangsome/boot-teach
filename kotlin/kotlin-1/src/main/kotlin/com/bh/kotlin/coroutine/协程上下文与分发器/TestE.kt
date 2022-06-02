package com.bh.kotlin.coroutine.协程上下文与分发器

import kotlinx.coroutines.*

/**
 *  对于父子协程来说，父协程总是会等待其所有的子协程的完成。对于父协程来说，他不必显示的去追踪由
 *  他所启动的所有的子协程，同时也不必调用子协程的Job.join()来等待子协程的完成。
 *
 *
 */
fun main() = runBlocking<Unit> {
    val request = launch {
        repeat(5) { i ->
            launch {
                delay((i+1)*100L)
                println("coroutine $i 执行完毕")
            }
        }
        println("hello")
    }
    // join() 直到request 中所有的子协程完成之后才会返回，执行下面的代码
    request.join()
    println("world")
}