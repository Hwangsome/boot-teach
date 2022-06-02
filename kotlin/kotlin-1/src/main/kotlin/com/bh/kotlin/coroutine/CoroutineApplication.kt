package com.bh.kotlin.coroutine

import kotlinx.coroutines.*

/**
 * 协程是什么、挂起是什么、挂起的非阻塞式是怎么回事?
 *
 *  协程就是切线程；(将协程中的代码切换到另外一个线程中去执行)
 *  挂起就是可以自动切回来的切线程；
 *  挂起的非阻塞式指的是它能用看起来阻塞的代码写出非阻塞的操作，就这么简单。
 *
 *
 *  协程就是 Kotlin 提供的一套线程封装的 API，但并不是说协程就是为线程而生的。
 *
 *  理解协程：
 *      1. 我们所有的代码都是跑在线程中的，而线程是跑在进程中的。
 *      2. 协程没有直接和操作系统关联，但它不是空中楼阁，它也是跑在线程中的，可以是单线程，也可以是多线程。
 *      3. 协程设计的初衷是为了解决并发问题，让 「协作式多任务」 实现起来更加方便。
 */
fun main() {
//    val launch = GlobalScope.launch {
//        delay(1000)// 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("kotlin coroutines")// 在延迟后打印输出
//    }
//    Thread.sleep(1000)
    val start = System.currentTimeMillis()
    val job:Job = GlobalScope.launch {
        println(getUser())
    }


    Thread.sleep(1100)
    val end = System.currentTimeMillis()
    println("${end-start}")


}


data class User(val name: String)

suspend fun getUser():User{
    val start = System.currentTimeMillis()
    delay(1000)
    val end = System.currentTimeMillis()
    println("${end-start}")
    return User("bh")
}

private suspend fun getResult(num: Int): Int {
    return withContext(Dispatchers.IO) {
        num * num
    }
}