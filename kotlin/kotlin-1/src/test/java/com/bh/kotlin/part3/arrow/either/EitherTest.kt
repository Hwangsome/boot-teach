//package com.bh.kotlin.part3.arrow.either
//
//import arrow.core.*
//import io.kotest.core.spec.style.ShouldSpec
//import io.kotest.matchers.shouldBe
//
///**
// * 这个类被设计为右偏。因此，正确的分支应该包含业务价值，例如一些计算的结果。左分支可以保存错误消息甚至异常。
// * 因此，值提取器方法（getOrElse）被设计在右侧：
// */
//class EitherTest : ShouldSpec({
//    val rightOnly: Either<String, Int> = Either.right(42)
//    val leftOnly: Either<String, Int> = Either.left("foo")
//
//    should("return Either.Right() ,if Either.Left() return params ") {
//        rightOnly.getOrElse { -1 } shouldBe 42
//        leftOnly.getOrElse { -1 } shouldBe -1
//    }
//
//    //甚至map和flatMap 方法都设计为使用右侧并`跳过左侧`：
//    should("with map and flatmap ") {
//        rightOnly.map { it % 2 }.getOrElse { -1 } shouldBe 0
//        //跳过左边，左边没有值，得到默认值为-1
//        leftOnly.map { it % 2 }.getOrElse { -1 } shouldBe -1
//        leftOnly.map { println(it) }
//
//        rightOnly.flatMap { Either.Right(it % 2) }.isRight() shouldBe true
//        leftOnly.flatMap { Either.Right(it % 2)}.isLeft()  shouldBe  true
//    }
//})
//
//
