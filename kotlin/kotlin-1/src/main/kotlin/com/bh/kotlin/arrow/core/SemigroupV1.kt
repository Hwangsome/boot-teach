package com.bh.kotlin.arrow.core


import arrow.typeclasses.Semigroup

/**
 * Semigroup是一个定义如何combine关联的功能接口。
 */
fun main() {
    val SemigroupResult= Semigroup.int().run { 1.combine(2) }
    println("SemigroupResult:$SemigroupResult")

    val SemigroupResult2=Semigroup.list<Int>().run {
        listOf(1, 2, 3).combine(listOf(4, 5, 6))
    }
    println("SemigroupResult2:$SemigroupResult2")
}