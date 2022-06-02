package com.bh.kotlin.arrow.core

import kotlinx.coroutines.*
import java.util.*

//使用GlobalScope单例对象调用launch
fun main() {

    GlobalScope.launch {

    }

    GlobalScope.async {

    }


    runBlocking {

    }



    val job = GlobalScope.launch {
        //调用其他挂起函数
        for(i in 1..5){
            delay(200)   //每隔1s打印一个数字
            println("--->$i")
        }
    }

    Timer().schedule(object: TimerTask(){
        override fun run() {
            println("-----------------------------3s时间到----------------------------")
            println("协程是否活动？${job.isActive}")          //true
            println("协程是否执行完毕？${job.isCompleted}")   //false
            println("协程是否取消？${job.isCancelled}")       //false
            job.cancel("中途取消协程，生命周期结束")
            println("协程是否活动？${job.isActive}")          //false
            println("协程是否执行完毕？${job.isCompleted}")   //false
            println("协程是否取消？${job.isCancelled}")      //true
        }
    }, 3000)   //3s后结束协程
    Thread.sleep(100000)  //阻止jvm退出
}
