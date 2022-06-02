package com.bh.kotlin.enums

fun main(currentDay: Day) {
    val dayOfWeek = Day.FRIDAY.dayOfWeek
    println("dayOfWeek = ${dayOfWeek}")


    val firstDay = Day.FRIDAY.firstDay()
    println("firstDay = ${firstDay}")

    println(Day.MONDAY is Day)

    val nextDay = Day.FRIDAY.nextDay()
    println("nextDay = ${nextDay}")

    when (currentDay){
        Day.MONDAY -> work()
        Day.FRIDAY-> work()
            //..
    }
}

fun work() {
    println("Working")
}