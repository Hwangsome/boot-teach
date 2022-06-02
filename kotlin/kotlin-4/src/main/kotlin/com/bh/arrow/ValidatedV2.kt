package com.bh.arrow


/**
 * 只是用到了value值，key并没有用到。这样，我们就想不在声明key，那么就需要使用下划线字符（_）作为key替代
 */

data class Book(val title: String, val author: String, val year: Int)

fun main() {
   mapOf(1 to "one", 2 to "two", 3 to "three").forEach { (_, value) ->
      println("$value")
   }
    val (title, author, year) = Book("Kotlin", "JetBrains", 2010)
    println("$title, $author, $year")   // Kotlin, JetBrains, 2010

    //如果只需要title变量，可以这样
    val (title1, _, _) = Book("Kotlin", "JetBrains", 2010)
    println("$title1")   // Kotlin


}