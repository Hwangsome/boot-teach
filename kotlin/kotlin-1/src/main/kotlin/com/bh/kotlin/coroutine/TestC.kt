package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext

import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis


fun log(msg: String) {
    println("[${Thread.currentThread().name}] $msg")

}

/**
 * -Dkotlinx.coroutines.debug
 *
 * 通过日志可以发现协程运行在`线程池`中。
 * [DefaultDispatcher-worker-3 @coroutine#2] one
 * [DefaultDispatcher-worker-3 @coroutine#3] two
 * [DefaultDispatcher-worker-1 @coroutine#5] 2
 * [DefaultDispatcher-worker-7 @coroutine#9] 6
 * ....
 *
 *
 * suspend function。即挂起函数，delay() 就是协程库提供的一个用于实现非阻塞式延时的挂起函数
 *
 * CoroutineScope。即协程作用域，GlobalScope 是 CoroutineScope 的一个实现类，用于指定协程的作用范围，可用于管理多个协程的生命周期，所有协程都需要通过 CoroutineScope 来启动
 *
 * CoroutineContext。即协程上下文，包含多种类型的配置参数。Dispatchers.IO 就是 CoroutineContext 这个抽象概念的一种实现，用于指定协程的运行载体，即用于指定协程要运行在哪类线程上
 *
 * CoroutineBuilder。即协程构建器，协程在 CoroutineScope 的上下文中通过 launch、async 等协程构建器来进行声明并启动。launch、async 均被声明为 CoroutineScope 的扩展方法
 *
 * 挂起函数不会阻塞其所在线程，而是会将协程挂起，在特定的时候才再恢复执行。
 * delay() 函数类似于 Java 中的 Thread.sleep()，而之所以说 delay() 函数是非阻塞的，
 * 是因为它和单纯的线程休眠有着本质的区别。例如，当在 ThreadA 上运行的 CoroutineA 调用了delay(1000L)
 * 函数指定延迟一秒后再运行，ThreadA 会转而去执行 CoroutineB，等到一秒后再来继续执行 CoroutineA。
 * 所以，ThreadA 并不会因为 CoroutineA 的延时而阻塞，而是能继续去执行其它任务，所以挂起函数并不会阻塞其所在线程，
 * 这样就极大地提高了线程的并发灵活度，最大化了线程的利用效率。而如果是使用Thread.sleep()的话，
 * 线程就只能干等着而不能去执行其它任务，降低了线程的利用效率
 * 协程是运行于线程上的，一个线程可以运行多个（几千上万个）协程。线程的调度行为是由操作系统来管理的，
 * 而协程的调度行为是可以由开发者来指定并由编译器来实现的，协程能够细粒度地控制多个任务的执行时机和执行线程，
 * 当线程所执行的当前协程被 suspend 后，该线程也可以腾出资源去处理其他任务
 *
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        val one:Deferred<Int>
        val two:Deferred<Int>
        withContext(Dispatchers.IO) {
             one = async{
                log("one")
                doSomethingUsefulOne()

            }
        }

        withContext(Dispatchers.Default) {
            two = async {
                log("two")
                doSomethingUsefulTwo()
        }



        }
        println("The answer is ${one.await() + two.await()}")
    }


    withContext(Dispatchers.IO) {
        for (i in 1 ..1000){
            async{
                log("$i")
                doSomethingUsefulTwo()
            }
        }
    }

    println("Completed in $time ms")
    //sampleEnd
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

suspend fun test(): Int= withContext(Dispatchers.IO){
     13
}

suspend fun test2()=coroutineScope{
    delay(1000L) // pretend we are doing
}
