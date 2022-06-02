package com.bh.kotlin.enums
/**
 * 枚举类需要值的时候，需要定义枚举类的属性与构造方法
 */
enum class Day(val dayOfWeek: Int):IDay {
    /**
     * 枚举类中的类型都是枚举类那种类型 比如 MONDAY
     *
     *  println(Day.MONDAY is Day)  true
     *
     *  可以将 MONDAY 。。。。 那些都理解为一个类
     */
    MONDAY ( 1 ) {
        override fun nextDay(): Day =TUESDAY
    },
    TUESDAY ( 2 ) {
        override fun nextDay(): Day =WEDNESDAY
    },
    WEDNESDAY ( 3 ) {
        override fun nextDay(): Day =THURSDAY
    },
    THURSDAY ( 4 ) {
        override fun nextDay(): Day =FRIDAY
    },
    FRIDAY ( 5 ) {
        override fun nextDay(): Day =SATURDAY
    },
    SATURDAY ( 6 ) {
        override fun nextDay(): Day =SUNDAY
    },
    SUNDAY ( 7 ) {
        override fun nextDay(): Day =MONDAY
    };

    override fun firstDay(): Day {
        return MONDAY
    }
}
interface IDay {
    fun firstDay(): Day

    fun nextDay(): Day
}
