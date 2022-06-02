package com.bh.kotlin.arrow.core

import arrow.core.Either
import arrow.core.Eval

import arrow.core.combineK
import arrow.core.filterOrElse
import arrow.core.filterOrOther
import arrow.core.flatMap
import arrow.core.getOrElse
import arrow.core.getOrHandle
import arrow.core.handleErrorWith
import arrow.core.leftIfNull
import arrow.core.right
import arrow.core.rightIfNotNull

fun main() {
    val right: Either<String, Int> = Either.Right(5)
    val left: Either<String, Int> = Either.Left("error!")
    /**
     * Either API
     * 1.
     *  isRight()
     *  isLeft()
     *  2.
     *
     */
    println(right.isRight())
    println(right.isLeft())

    /**
     * Either API
    b是调用者的包装的值
    a是调用者的包装的值
    fun <C> fold(ifLeft: (A) -> C, ifRight: (B) -> C): C = when (this) {
    is Right -> ifRight(b)
    is Left -> ifLeft(a)
    }
     * fold():调用者是Right类型，就将右边的值传给fold的第二个参数，
     */
    right.fold({
        println("operation failed with $it")
    }, {
        println("operation success with $it")
    })

    /**
     * fun <C> foldLeft(initial: C, rightOperation: (C, B) -> C): C
     * foldLeft向左折叠值，将右边的值与给定初始值进行操作。这里是将右边的值与给定的初始值进行相加的操作。
     */
    val foldLeftValue = right.foldLeft(1) { initial, right ->
        right + initial
    }
    println("foldLeftValue:$foldLeftValue")

    /**
     * 左右两边的值都折叠
    inline fun <C> bifoldLeft(c: C, f: (C, A) -> C, g: (C, B) -> C): C =
    fold({ f(c, it) }, { g(c, it) })
    给定一个初始值c，两个折叠函数f和g，将左边的值与c进行操作，将右边的值与c进行操作。
     */
    val bifoldLeftResult = left.bifoldLeft(1,
        { initial, left ->
            left.length + initial
        },
        { initial, right ->
            initial + right
        }
    )

    println("bifoldLeftResult:$bifoldLeftResult")

    /**
     *  inline fun <C> foldRight(initial: Eval<C>, crossinline rightOperation: (B, Eval<C>) -> Eval<C>): Eval<C>
     */
//    val foldRightValue = right.foldRight(Eval.now(1)) { right, initial ->
//        Eval.always { right + initial.value() }
//    }
//    println("foldRightValue.value():${foldRightValue.value()}")

    /**
     *  right ==>Right(5)
     *  swap: ==>Left(5)
     */
    val swap = right.swap()
    println("swap:$swap")

    val mapValue = right.map {
        it * 2
    }
    println("mapValue:$mapValue")

    /**
     * 映射左边的值
     */
    val mapLeftValue = left.mapLeft {
        it + "~~~"
    }
    println("mapLeftValue:$mapLeftValue")

    /**
     * 判断右边的值是否符合，如果是左边的值，直接返回false
    fun exists(predicate: (B) -> Boolean): Boolean =fold({ false }, { predicate(it) })
     */
    val rightExists = right.exists { it > 3 }
    println("rightExists:$rightExists")


    /**
     * orNull:如果是左边的值，直接返回null
     */
    val rightOrNull = right.orNull()
    val leftOrNull = left.orNull()
    println("rightOrNull:$rightOrNull")
    println("leftOrNull:$leftOrNull")

    /**
     * bimap 映射左右两边的值
    inline fun <C, D> bimap(leftOperation: (A) -> C, rightOperation: (B) -> D): Either<C, D> =
    fold({ Left(leftOperation(it)) }, { Right(rightOperation(it)) })
     */

    val rightBimap = right.bimap({
        it + "~~~"
    }, {
        it * 2
    }).fold({
        println("operation failed with $it")
        it
    }, {
        println("operation success with $it")
        it
    })
    println("rightBimap:$rightBimap")


    /**
     *
    inline fun <A, B, C> EitherOf<A, B>.flatMap(f: (B) -> Either<A, C>): Either<A, C> =
    fix().let {
    when (it) {
    is Right -> f(it.b)
    is Left -> it
    }
    }
     *
     * 当调用者是Right的时候执行f函数，并返回结果，否则返回调用者本身的值。
     * //执行f函数 返回的值是Right(10)
     * rightFlatMap:Right(10)
     * // 返回调用者本身
     * leftFlatMap:Left(error!)
     *
     *  先遍历，在判断是否是Right值，Right值再执行f函数
     * flat 压平的意思，Right值：执行f函数，返回。
     *                Left值：直接返回
     */
    val rightFlatMap = right.flatMap {
        Either.Right(it * 2)
    }
    val leftFlatMap = left.flatMap {
        Either.Right(it * 2)
    }
    println("rightFlatMap:$rightFlatMap")
    println("leftFlatMap:$leftFlatMap")


    /**
     * 将左边的值传入，经过lambda函数操作之后，返回一个值，然后将返回的值传入右边的值
     * inline fun <C> map(f: (B) -> C): Either<A, C> =flatMap { Right(f(it)) }
     *
     * 相当于是遍历，你进去的是啥，出来的还是啥。
     */
    val rightMap = right.map {
        it * 2
    }
    println("rightMap:$rightMap")
    left.map {
        println("it:$it")
    }


    /**
     * 获取Right值，如果是Left 对象，则给定一个默认值
     * inline fun <B> EitherOf<*, B>.getOrElse(default: () -> B): B =fix().fold({ default() }, ::identity)
     */
    val leftGetOrElse = left.getOrElse { 1 }
    println("leftGetOrElse:$leftGetOrElse")
    val rightGetOrElse = right.getOrElse { 1 }
    println("rightGetOrElse:$rightGetOrElse")


    /**
     * inline fun <A, B> EitherOf<A, B>.getOrHandle(default: (A) -> B): B =fix().fold({ default(it) }, ::identity)
     * 如果是Left值，给左值进行处理之后返回
     * 如果是Right值，获取Right值之后返回
     */
    val rightGetOrHandle = right.getOrHandle {
        it.length * 999
    }
    println("rightGetOrHandle:$rightGetOrHandle")

    /**
     * inline fun <A, B> EitherO，f<A, B>.filterOrElse(predicate: (B) -> Boolean, default: () -> A): Either<A, B> =flatMap { if (predicate(it)) Right(it) else Left(default()) }
     *
     * 如果predicate是true,返回Right值，否则返回返回Left包装的default值
     */
    val filterOrElse = right.filterOrElse({ a -> a > 20 }, { "2" })
    println("filterOrElse:$filterOrElse")


    /**
     *
    inline fun <A, B> EitherOf<A, B>.filterOrOther(predicate: (B) -> Boolean, default: (B) -> A): Either<A, B> =
    flatMap {
    if (predicate(it)) Right(it)
    else Left(default(it))
    }
     *
     *  判断Right值，断言通过，返回Right 值
     *  否则将处理过后的Right值封装到Left中返回
     */
    val filterOrOther = right.filterOrOther({ right -> right > 2 }, { right -> right.toString() + "aaa" })
    println("filterOrOther:$filterOrOther")


    /**
    inline fun <A, B> EitherOf<A, B?>.leftIfNull(default: () -> A): Either<A, B> =fix().flatMap { it.rightIfNotNull { default() } }
     */
    right.leftIfNull {

    }

    right.rightIfNotNull {

    }

//    left.handleErrorWith {
//
//    }

//    left.handleError {
//
//    }

    /**
     *  如果调用者是Right,直接返回
     *  如果调用者是Left,返回参数
     */
    val combineK = left.combineK(right)
    println("combineK:$combineK")


    val eitherResult:Either<NullPointerException,Int> = 1.right()
    println("eitherResult:$eitherResult")


//    println(test(1) { a, b ->
//        a + b
//    })
}

fun test(a: Int, add: (Int, Int) -> Int): Int {
    return add(a, a)
}