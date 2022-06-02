package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 几个挂起函数：withContext(),coroutineScope(),最后一个参数都是coroutineScope,即协程
 */
fun main() = runBlocking{
   val time= measureTimeMillis{
        GlobalScope.launch{
            launch{
                delay(1000)
            }
            launch {
                delay(1000)
            }
        }.join()
    }
    //1023
    println("$time")

}



