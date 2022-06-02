package com.bh.kotlin.base

/**
 *
 * inline: 声明在编译时，将函数的代码拷贝到调用的地方(内联)
 * oninline: 声明 inline 函数的形参中，不希望内联的 lambda
 * crossinline: 表明 inline 函数的形参中的 lambda 不能有 return
 *
 * https://juejin.cn/post/6844903838269308941
 */
fun main() {
    println(sum(1, 2))
}
fun sum(a: Int, b: Int): Int {
    return a + b
}

inline fun sum2(a: Int, b: Int): Int {
    return a + b
}