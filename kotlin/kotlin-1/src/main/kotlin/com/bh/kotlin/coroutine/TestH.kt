package com.bh.kotlin.coroutine

import kotlinx.coroutines.*

/**
start
500
withContext-1
withContext-2
complete
 */
fun main() = runBlocking<Unit>{
    println("start")
    launch{
        delay(500)
        println("500")
    }
    withContext(Dispatchers.IO){
        launch {
            delay(100)
            println("withContext-1")

        }
        delay(300)
        println("withContext-2")
    }
    println("complete")
}

/**
 * 主线程遇到挂起函数，不是阻塞，而是这里有一个事件循环机制
 * 会去执行前面合适的协程.
 *
 * 当`线程`遇到挂起函数比如（coroutineScope()，withContext()）的时候，线程从调用这个函数中返回到runBlocking函数的作用域（{}）中
 * 当挂起函数执行完毕的时候，线程才会往下执行
 */
suspend fun test1000()=coroutineScope{
    println("1000")
    delay(1000)
}