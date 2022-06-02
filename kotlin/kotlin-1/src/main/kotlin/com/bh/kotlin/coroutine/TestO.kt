package com.bh.kotlin.coroutine

import kotlinx.coroutines.*
import java.lang.Exception

/**
 * 关于父子协程的异常与取消的问题
 *
 * 协程的取消总是会沿着协程层次体系向上进行传播
 */
//fun main()= runBlocking<Unit> {
//
//     /**
//     value2 throws an exception
//     value is cancelled
//     computation failed
//     Exception in thread "main" java.lang.Exception
//      */
//     try {
//         failureComputation()
//     }finally {
//         println("computation failed")
//     }
//}
//
//
//private suspend fun failureComputation():Int = coroutineScope {
//
//     val value1=async<Int>{
//          try {
//               delay(3000)
//               50
//          }finally {
//              println("value is cancelled")
//          }
//     }
//
//     val value2=async<Int>{
//          Thread.sleep(200)
//          println("value2 throws an exception")
//          throw Exception()
//     }
//
//     value1.await()+value2.await()
//}

private suspend fun test10000()= GlobalScope.launch{
     launch{

     }
     launch{

     }
     launch{

     }
}