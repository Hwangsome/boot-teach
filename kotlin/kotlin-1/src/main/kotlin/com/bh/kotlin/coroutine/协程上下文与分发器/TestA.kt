package com.bh.kotlin.coroutine.协程上下文与分发器

import arrow.fx.asCoroutineContext
import com.bh.kotlin.coroutine.log
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

/**
 * 协程与线程之间的关系
 *
 * 协程上下文与分发器（coroutine Context 与Dispatchers）
 *
 * 协程总是会在某个上下文中执行，这个上下文实际上是由CoroutineContext类型的一个实例来表示的
 * 该实例是由Kotlin标准库来定义的。
 *
 * 协程上下文本质上是各种元素构成的一个集合。其中，主要的元素包括协程Job,以及分发器。
 * 所谓的分发器，其主要功能就是确定由哪个线程来之行我们所指定的协程代码
 *
 * 协程上下文包含了一个协程分发器(CoroutineDispatcher),协程分发器确定了到底由哪一个线程或者线程池来执行我们所指定的协程。
 * 协程分发器可以将协程的执行先知道一个具体指定的线程，也可以将协程的执行分发到一个线程池中，由线程池中的某一个线程来执行我们所指定的协程
 * 还可以不加任何限制的去执行我们所指定的协程代码。（在这种情况下，我们所指定的协程代码到底是由哪个线程或线程池来执行的是不确定的，他需要根据
 * 程序的实际执行情况方能确定；这种方式的协程分发器在一般开发中使用较少的，他只用在一些极为特殊的情况下）
 *
 * CoroutineDispatcher:
 * 由所有协程调度程序实现扩展的基类。
 * 以下标准实现由kotlinx.coroutines作为Dispatchers对象的属性提供：
 * Dispatchers.Default — 如果在其上下文中未指定调度程序或任何其他ContinuationInterceptor ，则所有标准构建器都将使用它。它使用共享后台线程的公共池。对于消耗 CPU 资源的计算密集型协程来说，这是一个合适的选择。
 * Dispatchers.IO — 使用按需创建的线程共享池，设计用于卸载 IO 密集型阻塞操作（如文件 I/O 和阻塞套接字 I/O）。
 * Dispatchers.Unconfined — 在当前调用帧中开始协程执行，直到第一次挂起，然后协程构建器函数返回。协程稍后将在相应挂起函数使用的任何线程中恢复，而不将其限制在任何特定线程或池中。 Unconfined调度程序通常不应在代码中使用。
 * 可以使用newSingleThreadContext和newFixedThreadPoolContext创建私有线程池。
 * 任意Executor可以通过asCoroutineDispatcher扩展函数转换为 dispatcher。
 *
 *  Dispatchers可以看做是CoroutineDispatcher的工具类，指定分发到哪里
 *
 *
 * 所有的协程构建器（coroutine builder）如launch 和async都会接收一个可选的CoroutineContext参数，该参数可用于显示指定新携程所运行的分发器以及其他
 * 上下文元素。
 *
 *
 * 结果：
 * [main] Dispatchers.Unconfined ,thread is main
 * [DefaultDispatcher-worker-1] Dispatchers.Default ,thread is DefaultDispatcher-worker-1
 * [DefaultDispatcher-worker-1] Dispatchers.IO ,thread is DefaultDispatcher-worker-1
 * [pool-1-thread-1] single thread executor ,thread is pool-1-thread-1
 * [main] no params ,thread is main
 *
 * 程序分析：
 *  1. 当通过launch来启动协程并且不指定协程分发器的时候，他会继承启动他的那个CoroutineScope的上下文分发器。对于
 *  这个例子来说，他会继承runBlocking的上下文，而runBlocking又是运行在main线程当中
 *
 *  2.Dispatchers.Unconfined 是一种很特殊的协程分发器，他在该示例中也是运行在main线程中，但实际上，其运行的机制与不指定
 *  协程分发器是完全不同的。在日常开发中使用的比较少。
 *
 *  3. Dispatchers.Default是默认的分发器，当协程通过GlobalScope来启动的时候，他会使用该默认的分发器来启动协程
 *  他会使用一个后台的(JVM的)共享线程池（默认等于cpu的核心数，最小为2个）来运行我们的协程代码，因此，launch(Dispatchers.Default)等价于Global.launch{}
 *
 *  4. 指定线程池：Executors.newSingleThreadExecutor().asCoroutineContext()，创建一个单线程的线程池，该线程池中的线程用来
 *  执行我们所指定的协程代码。在实际开发中，使用专门的线程来执行协程的代码代价是非常高的，因此在协程代码执行完毕后，我们必须要释放相应的资源
 *  这里就需要使用close方法来关闭相应的协程分发器，从而释放掉资源；也可以将协程分发器存储到一个顶层变量当中，以便在程序的其他地方进行复用
 *
 *
 *  继承关系：
 *  CoroutineDispatcher 继承了AbstractCoroutineContextElement(ContinuationInterceptor)，实现了 ContinuationInterceptor
 *  AbstractCoroutineContextElement 实现了Element
 *  interface Element 实现了 CoroutineContext
 *  所以CoroutineDispatcher 先天就继承了 CoroutineContext，所以传参CoroutineContext可以传递CoroutineDispatcher的类型
 *
 * //true 说明 CoroutineDispatcher 就是CoroutineContext的"实现类"（注意双引号）
 *  println(Dispatchers.IO is CoroutineContext)
 *
 */
fun main() =runBlocking<Unit>{

    launch{
        log("no params ,thread is ${Thread.currentThread().name}")
    }.invokeOnCompletion {  }

    launch(Dispatchers.Unconfined){
        //加上这个之后，协程就不运行在main线程中了
        delay(100)
        log("Dispatchers.Unconfined ,thread is ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default){
        log("Dispatchers.Default ,thread is ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO){
        log("Dispatchers.IO ,thread is ${Thread.currentThread().name}")
    }


    /**
     * 指定使用线程池
     *  ExecutorService.asCoroutineContext() 。asCoroutineContext()是ExecutorService的扩展方法
     *      ExecutorService extends Executor
     *
     *      Executors是ExecutorService的工具类，可以创建定义好的线程池
     *
     */
    val thread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(thread){
        log("single thread executor ,thread is ${Thread.currentThread().name}")
        /**
         * 关闭这个协程调度器并关闭它的执行器。
         * 如果此调度程序是全局的并且无法关闭，它可能会抛出异常。
         */
        thread.close()
    }



}