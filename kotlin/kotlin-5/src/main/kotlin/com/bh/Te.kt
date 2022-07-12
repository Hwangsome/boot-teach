package com.bh

fun main() {
    mapOf(1 to "first",2 to "second").toList().map {
        println(it.first)
        println(it.second)
    }
}
