package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis


/**
 *suspend function。即挂起函数，delay() 就是协程库提供的一个用于实现非阻塞式延时的挂起函数
CoroutineScope。即协程作用域，GlobalScope 是 CoroutineScope 的一个实现类，用于指定协程的作用范围，可用于管理多个协程的生命周期，所有协程都需要通过 CoroutineScope 来启动
CoroutineContext。即协程上下文，包含多种类型的配置参数。Dispatchers.IO 就是 CoroutineContext 这个抽象概念的一种实现，用于指定协程的运行载体，即用于指定协程要运行在哪类线程上
CoroutineBuilder。即协程构建器，协程在 CoroutineScope 的上下文中通过 launch、async 等协程构建器来进行声明并启动。launch、async 均被声明为 CoroutineScope 的扩展方法
 */
fun main(): Unit =runBlocking{
    log("start")
    val time = measureTimeMillis {
        // 起一个协程，运行在线程 worker-1上
        GlobalScope.launch {
            log("${this.coroutineContext}")
            //又起了一个协程 ，运行在 worker -2上
            launch {

                delay(300)
                log("launch A")
                //又又起了一个协程，运行在 worker-3上
            }
            launch {

                delay(300)
                log("launch B")
                //又又起了一个协程，运行在 worker-3上
            }
            launch {

                delay(300)
                log("launch C")
                //又又起了一个协程，运行在 worker-3上
            }

            log("GlobalScope")
        }
    }
    Thread.sleep(700)
    log("end")
    println("$time")

}

suspend fun delayTime(time: Long): Long {
    delay(time)
    return time
}

suspend fun get() = coroutineScope {
    val delayTime1 = async{delayTime(1000)}
    val delayTime2 = async{delayTime(2000)}

    val l = delayTime1.await() + delayTime2.await()
    println(l)
}

