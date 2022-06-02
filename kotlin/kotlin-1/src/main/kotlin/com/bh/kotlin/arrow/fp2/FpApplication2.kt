//package com.bh.kotlin.arrow.fp2
//
//
//
//import arrow.fx.IO
//import arrow.fx.coroutines.Atomic
//import arrow.fx.coroutines.parTraverse
//import kotlinx.coroutines.*
//
//
//suspend fun main() {
//
//   runBlocking {  }
//    val count = Atomic(0)
//
//    (0 until 20_000).parTraverse {
//        count.update(Int::inc)
//    }
//    println(count.get())
//}
//
//
//fun number1(): IO<Int> = IO.just(1)
//fun triple(): IO<Triple<Int, Int, Int>> =
//    number1().flatMap { a ->
//        number1().flatMap { b ->
//            number1().map { c ->
//                Triple(a, b, c)
//            }
//        }
//    }
//
//suspend fun number2(): Int = 1
//
//suspend fun triple2(): Triple<Int, Int, Int> =
//    Triple(number2(), number2(), number2())
