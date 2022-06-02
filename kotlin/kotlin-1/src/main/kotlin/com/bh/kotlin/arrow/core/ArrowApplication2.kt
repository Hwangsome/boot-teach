package com.bh.kotlin.arrow.core

import arrow.core.Either


/**
 *
 */
fun main() {

    /**
     * mapLeft :如果对象是Either.Right 返回值就是Right
     *          如果对象是Either.Left 就是将值映射到左值，然后再进行逻辑操作
     */
//    val r : Either<Int, Int> = Either.Right(7)
//    val rightMapLeft = r.mapLeft {it + 1}
//    val l: Either<Int, Int> = Either.Left(7)
//    val leftMapLeft = l.mapLeft {it + 1}

//    //rightMapLeft = Right(b=7)
//    println("rightMapLeft = $rightMapLeft")
//    //leftMapLeft = Left(a=8)
//    println("leftMapLeft = $leftMapLeft")

    /**
     * 交换 左右侧的值
     */
//    val swap = r.swap()
//    //swap = Left(a=7)
//    println("swap = ${swap}")

    /**
     * 任意类型的对象都可以调用
     * left()、right()、contains()和方法
     * 使得使用getOrElse() 和getOrHandle()成为可能
     */
//    val right =7.right()
    /**
     * getOrElse
     */
//    right.getOrElse {
//
//    }
    /**
     * getOrHandle
     */
//    right.getOrHandle {
//
//    }
    ////right = Right(b=7)
//    println("right = ${right}")

    /**
     * contains:Either是否包含某个值
     */
//    val x = 7.right()
//    val contains7 = x.contains(7)
//    //contains7 = true
//    println("contains7 = $contains7")

    /**
     * 如果右侧没有值，则使用默认值
     */
//    val x = "hello".left()
//    val getOr7 = x.getOrElse { 7 }
//    //getOr7 = 7
//    println("getOr7 = $getOr7")

    /**
     * getOrHandle调用对象是左侧的时候，it就是左侧的值
     * 右侧的时候就是获取值
     */
//    val getOrHandle = x.getOrHandle { "$it world!" }
//    println("getOrHandle = ${getOrHandle}")


    /**
     * 要基于谓词创建 Either 实例，请使用Either.conditionally()方法。
     * 它将评估作为第一个参数传递的表达式，如果表达式评估为false它将给出Either.Left<L>第二个参数的构建。
     * 如果表达式的计算结果为 a true，它将采用第三个参数并给出Either.Right<R>：
     */


    /**
     * fold: 此操作将从 Either 中提取值，如果值为Left ，则提供默认值
     */
//    val x : Either<Int, Int> = 7.right()
//    val fold = x.fold(
//        //如果值为Left ，则提供默认值
//        { 1 },
//        //如果值为 Right，可以获取到it为右侧的值
//        { it + 3 }
//    )


//    val r: Either<Throwable, Int> = Either.Left(NumberFormatException())
//    val httpStatusCode = r.getOrHandle {
//        when(it) {
//            is NumberFormatException -> 400
//            else -> 500
//        }
//    }
//
//    //httpStatusCode = 400
//    println("httpStatusCode = ${httpStatusCode}")

    /**
     *  如果Right的值是null,则将值转换为Left类型的值
     *  如果Right的值不为null,则返回的还是Right的值
     *
     *  同理rightIfNull
     */
//    val value1 = Right(12).leftIfNull { -1 }
//    //value1 = Right(b=12)
//    println("value1 = $value1")
//
//    //value2 = Left(a=-1)
//    val value2 = Right(null).leftIfNull { -1 }
//    println("value2 = $value2")
//
//    val value3 = Left(12).leftIfNull { -1 }
//    //value3 = Left(a=12)
//    println("value3 = $value3")
//
//    val value4 = Left(null).leftIfNull { -1 }
//    //value4 = Left(a=null)
//    println("value4 = $value4")


    /**
     * Either.catch exceptions
     * 有时您确实需要与可能引发异常的代码进行交互。在这种情况下，您应该降低引发异常的可能性。catch您可以通过使用该功能来做到这一点。
     */




}



fun potentialThrowingCode(): String = throw RuntimeException("Blow up!")

suspend fun makeSureYourLogicDoesNotHaveSideEffects(): Either<Error, String> =
    Either.catch { potentialThrowingCode() }.mapLeft { Error.NotANumber }



sealed class Error {
    object NotANumber : Error()
    object NoZeroReciprocal : Error()
}