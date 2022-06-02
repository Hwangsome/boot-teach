//package com.bh.kotlin.part3.arrow.eval
//
//import arrow.core.Eval
//import io.kotest.core.spec.style.ShouldSpec
//import io.kotest.matchers.shouldBe
//
////https://www.baeldung.com/kotlin/arrow
//class EvalTest:ShouldSpec({
//    val now = Eval.now(1)
//    var counter : Int = 0
//
//    //map和flatMap操作将延迟执行：
//    now.map { x ->counter++;x+1 }
//
//    should("map和flatMap操作将延迟执行"){
//        counter shouldBe 0
//    }
//
//
//
//})