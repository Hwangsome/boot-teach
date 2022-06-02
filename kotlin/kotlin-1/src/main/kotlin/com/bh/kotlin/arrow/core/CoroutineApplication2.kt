package com.bh.kotlin.arrow.core

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.EmptyCoroutineContext

//使用GlobalScope单例对象调用launch
fun main() :Unit= runBlocking{
    GlobalScope.launch {

        println(this)
        println("hello")
    }

//    MainScope()
//
//    CoroutineScope(context = EmptyCoroutineContext).launch {
//
//    }
}
