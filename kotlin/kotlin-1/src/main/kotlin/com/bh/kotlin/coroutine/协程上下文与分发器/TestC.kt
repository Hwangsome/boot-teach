package com.bh.kotlin.coroutine.协程上下文与分发器

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking


/**
 * Job的使用方式以及在Context中的具体应用
 * 协程的Job是归属于其上下文（Context）的一部分，Kotlin为我们提供了一种简洁的手段来通过协程上下文获取到协程自身的Job对象
 *
 * coroutineContext 是CoroutineScope的属性,在CoroutineScope中使用这个属性可以省略this。
 * CoroutineContext 是协程的持久上下文。它是一组Element实例的索引集。索引集是set和map之间的混合。该集合中的每个元素都有一个唯一的Key
 *
 * 我们可以通过coroutineContext[Job]表达式来访问上下文中的Job对象
 */
fun main() = runBlocking<Unit> {
//    val job = this.coroutineContext[Job]
    val job = coroutineContext[Job]
    println(job)
}