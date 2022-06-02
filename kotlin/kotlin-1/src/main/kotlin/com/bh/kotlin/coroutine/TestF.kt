package com.bh.kotlin.coroutine


import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


fun main(): Unit = runBlocking {
    println(this)
    launch {
        println(this)
    }
    launch {
        println(this)
    }


}


/**
 * 创建一个CoroutineScope并使用此范围调用指定的挂起块。提供的范围从外部范围继承其coroutineContext ，但覆盖上下文的Job 。
 * 这个函数是为并行分解工作而设计的。当此范围内的任何子协程失败时，此范围将失败并且所有其余的子协程都将被取消
 * （有关不同的行为，请参见supervisorScope ）。一旦给定块及其所有子协程完成，此函数就会返回。
 *
 * 类比runBlocking.
 * 下面的会创建一个coroutineScope，会等待所有启动的子协程全部完成后自身才会完成。
 */
suspend fun test5() = coroutineScope {
    launch {

    }
    async {

    }

}


suspend fun test6() = withContext(Dispatchers.IO) {

}


suspend fun test7() {

}

