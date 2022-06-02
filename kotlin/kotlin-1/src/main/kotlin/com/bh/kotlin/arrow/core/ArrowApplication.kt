package com.bh.kotlin.arrow.core//package com.bh.kotlin.arrow
//
//import arrow.core.Either
//import arrow.core.flatMap
//import arrow.core.getOrElse
//
///**
// * Either用于在第一个错误时短路计算
// *
// * 通常来说 Either的右侧用来保存成功的值，左侧用来保存异常的值
// * 因为Either是右偏的，所以可以为他定义一个Monad实例，由于我们只希望在Right的情况下继续计算（如右偏性质所捕获），
// * 我们`固定`左侧类型参数并保留右侧自由。
// *
// *
// *
// * 下面是一些api的介绍
// */
//fun main() {
//    val right: Either<String, Int> = Either.Right(5)
////    println("right=${right}")
//
//    /**
//     * isRight
//     */
////    println(right.isRight())
//
//
//    /**
//     * fold
//     */
////    right.fold({
////        //如果调用者是Left 执行这里的逻辑
////        println("left: ${it}")
////    },{
////        //如果调用者是Right 执行这里的逻辑
////        println("right: ${it}")
////    })
//
//
//    /**
//     * map :如果调用者是right，则（it）参数都是right的值
//     *      如果调用者是left,则（it）参数没有值
//     *
//     *       map 和 flatMap 方法是右偏的：
//     *       fun <A, B, C> EitherOf<A, B>.flatMap(f: (B) -> Either<A, C>): Either<A, C>
//     *       可以看上面的函数声明，都是固定了左侧，然后看f: ，传进去的参数都是B，就是调用这个方法的对象的右侧的值
//     *       然后Either<A, C> 作为f: 的返回值的同时也作为flatMap 这个函数的返回值
//     */
////    right.map {
////        println("it :${it}")
////    }
//
////    right.flatMap {
////        Either.Right(100)
////    }.map {
////        //100
////        println(it)
////    }
//
//
////    val left: Either<String, Int> = Either.Left("Something went wrong")
////    val value = left.flatMap{ Either.Right(it + 1) }
////    //value = Left(a=Something went wrong)
////    println("value = $value")
//
//
//    /**
//     * 使用 Either 而不是异常,我们在返回类型中明确说明我们的某些函数可能会失败
//     */
////    val notANumber = parse("not a number")
////    val number = parse("2")
////    //notANumber = Left(a=java.lang.NumberFormatException: not a number is not a valid integer.)
////    println("notANumber = $notANumber")
////    // number = Right(b=2)
////    println("number = $number")
////
////    val magic0 = magic("0")
////    val magic1 = magic("1")
////    val magicNotANumber = magic("Not a number")
////    //magic0 = Left(a=java.lang.IllegalArgumentException: Cannot take reciprocal of 0.)
////    println("magic0 = $magic0")
////    //magic1 = Right(b=1.0)
////    println("magic1 = $magic1")
////    //magicNotANumber = Left(a=java.lang.NumberFormatException: Not a number is not a valid integer.)
////    println("magicNotANumber = $magicNotANumber")
//
//
//    /**
//     * 使用模式 匹配与 Either结合
//     *
//     * arrow 还规定了左侧的变量名为a ,右侧的变量名为b,所以我们可以通过Either对象.a 或Either对象.b来获取Either包含的值
//     */
////    val x = magic("Not a number!")
////    val value2 = when(x){
////        //data class Left<out A> @PublishedApi internal constructor(val a: A)
////        is Either.Left -> when(x.a){
////            is NumberFormatException -> "Not a number!"
////            is IllegalArgumentException -> "Can't take reciprocal of 0!"
////            else -> "Unknown error"
////        }
////        //data class Right<out B> @PublishedApi internal constructor(val b: B)
////        is Either.Right -> "Got reciprocal: ${x.b}"
////    }
////    println("value2 = $value2")
//
//
//
//}
//
//
///**
// * 使用 Either 而不是异常,我们在返回类型中明确说明我们的某些函数可能会失败
// */
//fun parse(s: String): Either<NumberFormatException, Int> =
//    if (s.matches(Regex("-?[0-9]+"))) Either.Right(s.toInt())
//    else Either.Left(NumberFormatException("$s is not a valid integer."))
//
//
//fun reciprocal(i: Int): Either<IllegalArgumentException, Double> =
//    if (i == 0) Either.Left(IllegalArgumentException("Cannot take reciprocal of 0."))
//    else Either.Right(1.0 / i)
//
//fun stringify(d: Double): String = d.toString()
//
///**
// * 使用flatMap和map之类的组合符，我们可以将我们的函数组合在一起
// */
//fun magic(s: String): Either<Exception, String> =
//    parse(s).flatMap { reciprocal(it) }.map { stringify(it) }
//
