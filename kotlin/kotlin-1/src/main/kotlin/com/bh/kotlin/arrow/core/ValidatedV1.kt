package com.bh.kotlin.arrow.core

import arrow.core.Invalid
import arrow.core.Nel
import arrow.core.NonEmptyList
import arrow.core.Valid
import arrow.core.Validated
import arrow.core.Validated.Companion.invalidNel
import arrow.core.Validated.Companion.validNel
import arrow.core.ValidatedNel
import arrow.core.left
import arrow.core.right
import arrow.core.valid
import arrow.core.validNel


/**
 * NonEmptyList ==>Nel
 */
fun main() {
    val valid = Valid(1)
    val invalid = Invalid("invalid")


//    val result:Validated<NullPointerException, Int> = Valid(1)
    val result:Validated<NullPointerException, Int> = 1.valid()
    val result2:Validated<NullPointerException, Int> = Invalid(NullPointerException())
    println(result)


    /**
     * 当值为Invalid 并存储在List中的时候，我们希望确保他不为空
     *
     * 我们希望在其中存储一些内容来描述发生的错误/失败，
     * 这可以使用NonEmptyList来确保，他保证所环绕的List始终具有至少一个元素。
     *
     * NonEmptyList和Valided可以一起使用，事实上，这种组合非常常见，Arrow为我们提供了一个类型
     * 别名以及有用的函数，我们可以使用这些函数来对这种结构进行操作
     *
     * 我们可以使用内置函数 fold,getOrElse,map....来转换Validated的内部内容
     */

    val validNel = 1.validNel()
    println("validNel:$validNel")

    /**
     * 使用when表达式来提取Validated的内部内容
     */
    when(result2) {
        is Invalid -> println("invalid:${result2.value}")
        is Valid -> println("valid:${result2.value}")
    }

    /**
     * fold
     * 第一个函数解决了无效的情况。这里的意思就是我们发现一个错误，我们就返回0，是Valid我们就返回Valid的值，在
     * 这里我们可以操作Valid的值。
     */
    val test =result2.fold(
        {
            println("invalid fold :$it")
            0
        },
        {
            println("valid fold :$it")
                    it+1
        }
    )
    println("test:$test")

    /**
     *
     * map允许我们转换Validated的值,他只对Valid情况起作用，这就是为什么我们说Validated也是正确的。
     * 如果我们正在映射并且存在无效，那么在这种情况下，将永远不会应用转换，并且不改变无效
     */
    val mapResult= result.map {
        it*2
    }
    println("mapResult : $mapResult")

    val toValidatedNelResult =Invalid(NullPointerException()).toValidatedNel()
    println("toValidatedNelResult: $toValidatedNelResult")

//    val nelResult= Nel(1, 2, 3).validNel()
//    println("nelResult: $nelResult")
//    nelResult.map {
//        println("it.all: ${it.all}")
//        println("it.head: ${it.head}")
//        println("it.tail: ${it.tail}")
//    }

    /**
     * 使用对象去调用的方法：
     */
    //void()
    /**
     * voidValid=Validated.Valid(kotlin.Unit)
     * voidInvalid=Validated.Invalid(java.lang.NullPointerException)
     *
     * 右边有值的时候，将他映射为Unit
     */
    var voidValid = result.void()
    var voidInvalid = result2.void()
    println("voidValid=$voidValid")
    println("voidInvalid=$voidInvalid")


    /**
     * Valid值（右边的时候）：将集合中的值映射出来再放到Valid中
     *
     * traverseInValid=[]
     * traverseValid=[Validated.Valid(1), Validated.Valid(2), Validated.Valid(3)]
     */
    var traverseInValid =result2.traverse { listOf(it) }
    var traverseValid =result.traverse { listOf(1,2,3) }
    println("traverseInValid=$traverseInValid")
    println("traverseValid=$traverseValid")

    /**
     * traverseEither
     *
     * fa(1) -> Either.Right(2)
     */
    var traverseEitherValid =result.traverseEither { 2.right() }
    println("traverseEitherValid=$traverseEitherValid")


    var traverseEitherInValid =result2.traverseEither { 1.left() }
    println("traverseEitherInValid=$traverseEitherInValid")

    2.right().map {
        Validated.Valid(it)
    }.map {
        println(it)
    }

}



