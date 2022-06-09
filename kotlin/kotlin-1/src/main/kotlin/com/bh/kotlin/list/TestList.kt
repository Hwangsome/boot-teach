package com.bh.kotlin.list

fun main() {
    var beforeList:Collection<People> = listOf(People("1","张三", 18), People("2","李四", 20), People("1","张三", 19))
    val beforeMap = beforeList.associateBy {
        it.id
    }
    //遍历beforeMap
    for ((key, value) in beforeMap) {
        println("key:$key, value:$value")
    }

    val result= listOf("1","2","3","4","5").map {
        it+"a"
    }.filter {
        it.contains("1")
    }
}