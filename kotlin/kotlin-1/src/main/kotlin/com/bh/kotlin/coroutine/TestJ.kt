package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.withTimeoutOrNull


/**
 *
 *
 * 由withTimeOut函数调用所抛出的TimeoutCancellationException异常是CancellationException的子类
 * 当该异常抛出时，我们并未在控制台看到整个异常堆栈信息，这是因为在取消的协程当中，CancellationException
 * 被认为是一种协程完成的正常原因而已。不过我们在该实例中，是在main函数中使用了withTimeOut函数的调用
 *
 * 既然CancellationException仅仅只是个异常而已，所有的资源也都会以通常的方式来关闭，那么我们就可以将相关的代码放到一个try..catch
 * 块中，此外，kotlin还提供了另外一个更加友好的函数调用：withTimeoutOrNull;从功能角度来看，他非常类似于withTimeout，不过
 * 当超时发生的时候，他并不会抛出CancellationException异常，而是直接返回Null
 *
 * 对于withTimeOut函数调用来说，如果将其放置到try...catch块中，那么调用形式如下：
 *  try {
 *      ...
 *  }catch(ex:Exception){
 *      ...
 *  }
 */
fun main()=runBlocking {
        val result =withTimeoutOrNull(1900){
                repeat(50){
                        i->
                        println("hello $i")
                        delay(300)
                }
                "hello world"
        }
        //null
        println("result is $result")
}