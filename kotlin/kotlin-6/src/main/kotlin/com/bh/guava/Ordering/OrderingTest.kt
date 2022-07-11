package com.bh.guava.Ordering

import com.google.common.collect.Ordering

fun main() {
    val persons = listOf(Person("bh", 18), Person("bb", 19), Person("hh", 25))
    val comparePersonWithOrdering = comparePersonWithOrdering(persons)
    println("comparePersonWithOrdering=$comparePersonWithOrdering")
}

private fun comparePersonWithOrdering(persons: List<Person>): Boolean {
    return when {
        persons.all { it is Person } -> {
            Ordering.natural<Int>().isOrdered(persons.map { it.age })
        }
        else -> false
    }
}
