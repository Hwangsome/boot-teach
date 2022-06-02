package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 每一个协程构建器（包括runBlocking）都会向其代码块作用域中添加一个CoroutineScope实例，我们可以在该作用域中启动协程，
 * 而无需显式将其join到一起，这是因为外部协程（在下面的事例中就是runBlocking）会等待该作用域中的所有启动的协程完成后才会完成。
 */
fun main() =runBlocking{

   val time= measureTimeMillis {
       //下面启动的协程都会等待下面的协程执行完才会完成
        GlobalScope.launch{
            launch{
                delay(1000)
                launch{
                    delay(1000)
                    launch{
                        delay(1000)
                    }
                }
            }
        }.join()
    }

    println("$time")


}