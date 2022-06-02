package com.bh.kotlin.arrow

import com.bh.kotlin.coroutine.log
import kotlinx.coroutines.*

fun main() {
    val job=GlobalScope.launch {
        withContext(Dispatchers.IO){
            for (i in 1 .. 1000){
                log("$i")
            }
        }

        withContext(Dispatchers.IO){
            log("2")
            delayTime(500)
        }
    }
   Thread.sleep(590)
}

suspend fun delayTime(time:Long){
  delay(time)
}