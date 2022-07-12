package com.bh.guava.Ordering

data class Person(
    val name: String,
    val age: Int
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        // compareTo ：如果返回值是负数，表示比较的数小于other,如果返回值是正数,反之大于other，返回值是0，则比较的值等于other
        return age.compareTo(other.age)
    }
}
