package com.bh.kotlin.coroutine.qa

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 *
 * job2
 * job1
 */
fun main() =runBlocking<Unit>{
    val job = launch {
        delay(1000)
        println("job1")
    }
//    job.join() // waits for job's completion
    val job2 = launch {
        delay(500)
        println("job2")
    }
//    job.cancel()
    job.join()


//    job.join()
    println("test")


}