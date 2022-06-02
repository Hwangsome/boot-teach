package com.bh.kotlin.coroutine.协程上下文与分发器

import com.bh.kotlin.coroutine.log
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Dispatchers.Unconfined 协程分发器会在调用线程去启动协程，但仅仅会持续到第一个挂起点，当挂起结束后程序恢复执行时，
 * 他会继续执行协程的代码，但这时执行的协程是由之前所调用的挂起函数来决定的（这个挂起函数式由哪个线程来执行的，那么后面的代码就会由
 * 这个线程来执行）。Dispatchers.Unconfined协程分发器适用于这样的一些协程：他不会消耗CPU的时间，同时也不会更新任何共享的数据（特定于具体的线程）
 *
 * Dispatchers.Unconfined是一种高级的机制，他对于某些特殊的情况是很有帮助作用的：协程执行的分发是不需要的，或者会产生意料之外的副作用
 * 这是因为协程中的操作必须要立刻执行
 *
 * 我们在日常的代码编写中，几乎不会使用Dispatchers.Unconfined这种协程分发器
 */
fun main()  = runBlocking<Unit>{


    /**
     *
     * [main] Dispatchers.Unconfined ,thread is main
     * [main] no params ,thread is main
     * [kotlinx.coroutines.DefaultExecutor] Dispatchers.Unconfined ,thread is kotlinx.coroutines.DefaultExecutor
     * [main] no params ,thread is main
     */

    launch(Dispatchers.Unconfined){
        log("Dispatchers.Unconfined ,thread is ${Thread.currentThread().name}")
        delay(100)
        log("Dispatchers.Unconfined ,thread is ${Thread.currentThread().name}")
    }

    launch{
        log("no params ,thread is ${Thread.currentThread().name}")
        delay(200)
        log("no params ,thread is ${Thread.currentThread().name}")
    }
}