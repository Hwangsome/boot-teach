package com.bh.kotlin.arrow.core

import arrow.core.Eval

/**
 * Eval是一个Mona,他控制一个值评估或者产生一个值。
 */
fun main() {
//    println(Eval.One.value())


    /**
     * Now
     * Later
     * Always
     */
    var result = Eval.always { "Hello" }.flatMap {
        if (it.contains("h",ignoreCase = true)){
            Eval.now(it.toUpperCase())
        }else{
            Eval.now(it.toLowerCase())
        }
    }.value()
    println(result)
}