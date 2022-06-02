package com.bh.kotlin.coroutine

import kotlinx.coroutines.*


/**
 * 除去不同的协程构建器所提供的协程作用域（coroutine scope）外，我们还可以通过coroutineScope builder来声明自己的协程作用域
 * 该构件器会创建一个协程作用域，并且会等待所有启动的子协程全部完成后自身才会完成。
 *
 * runBlocking与coroutineScope之间的主要差别在于，后者在等待所有子协程完成其任务时不会阻塞当前的线程
 *
 * 1. runBlocking并非挂起函数，也就是说，调用它的线程会一直位于该函数之中，知道协程执行完毕为止
 * 2. coroutineScope是挂起函数，也就是说，如果其中的协程挂起，那么coroutineScope函数也会挂起。
 * 这样，创建coroutineScope的外层函数就可以继续在同一个线程中执行了，该线程会 逃离coroutineScope之外，并且可以做一些其他的事情
 *
 *
 * person
 * my job 1
 * hello world
 * my job 2
 * welcome
 */
fun main() =runBlocking<Unit>{

    launch {
        delay(1000)
        println("my job 1")
    }
    println("person")

    /**
     * coroutineScope 挂起函数，意味着它里面的所有的子协程全部完成才标记着 coroutineScope这个函数的完成
     * 对于当前的线程来说，遇到coroutineScope函数的时候，这个函数没有完成的时候，线程是无论如何都不可能去执行coroutineScope
     * 函数调用的下一行代码。即println("welcome")这行代码在coroutineScope函数没有完成的时候无论如何都不可能执行
     *
     * 线程在执行到这里的时候，并不是阻塞住了，而是有个事件循环，最典型的就是，这里的launch这个协程，线程又去执行这个协程了
     * 所以说并不是阻塞住。
     * 查看runBlocking的源码发现有一个 val eventLoop: EventLoop?
     */
    coroutineScope{
        launch{
            delay(2000)
            println("my job 2")
        }
        delay(1500)
        println("hello world")
    }
    println("welcome")
}